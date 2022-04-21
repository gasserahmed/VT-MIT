package edu.vt.cs.cs5254.gallery

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import edu.vt.cs.cs5254.gallery.api.GalleryItem
import edu.vt.cs.cs5254.gallery.databinding.FragmentGalleryBinding
import edu.vt.cs.cs5254.gallery.databinding.FragmentPhotoMapBinding

private const val TAG = "PhotoMapFragment"

class PhotoMapFragment : MapViewFragment() {
    private val viewModel: PhotoMapViewModel by viewModels()
    private lateinit var thumbnailDownloader: ThumbnailDownloader<Marker>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val responseHandler = Handler(Looper.getMainLooper())
        thumbnailDownloader =
            ThumbnailDownloader(responseHandler) { marker, bitmap ->
                setMarkerIcon(marker, bitmap)
            }
        lifecycle.addObserver(thumbnailDownloader.fragmentLifecycleObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateMapView(
            inflater,
            container,
            savedInstanceState,
            R.layout.fragment_photo_map,
            R.id.map_view
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onMapViewCreated(view, savedInstanceState) { googleMap -> {} }
        viewModel.galleryItemLiveData.observe(
            viewLifecycleOwner
        ) { galleryItems ->
            updateUI(galleryItems)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner

            .lifecycle.removeObserver(

                thumbnailDownloader

                    .viewLifecycleObserver

            )
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(
            thumbnailDownloader

                .fragmentLifecycleObserver

        )
    }

    private fun updateUI(galleryItems: List<GalleryItem>) {

        // if the fragment is not currently added to its activity, or
        // if there are not gallery items, do not update the UI
        if (!isAdded || galleryItems.isEmpty()) {
            return
        }

        Log.i(TAG, "Gallery has " + galleryItems.size + " items")

        // remove all markers, overlays, etc. from the map
        googleMap.clear()

        val bounds = LatLngBounds.Builder()
        val latLngGalleryItems =
            galleryItems.filterNot { it.latitude == "0" && it.longitude == "0" }
        for (item in latLngGalleryItems) {
            // log the information of each gallery item with a valid lat-lng
            Log.i(
                TAG,
                "Item id=${item.id} " +
                        "lat=${item.latitude} long=${item.longitude} " +
                        "title=${item.title}"
            )
            // create a lan-lng point for the item and add it to the lat-lng bounds
            val itemPoint = LatLng(item.latitude.toDouble(), item.longitude.toDouble())
            bounds.include(itemPoint)

            // create a marker for the item and add it to the map
            val itemMarker = MarkerOptions().position(itemPoint).title(item.title)
            val marker = googleMap.addMarker(itemMarker)
            marker?.tag = item.id
            marker?.let { thumbnailDownloader.queueThumbnail(it, item.url) }
        }

        Log.i(TAG, "Expecting ${latLngGalleryItems.size} markers on the map")
    }
}