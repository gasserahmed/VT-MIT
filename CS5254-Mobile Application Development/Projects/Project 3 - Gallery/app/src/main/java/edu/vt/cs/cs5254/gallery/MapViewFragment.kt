package edu.vt.cs.cs5254.gallery

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker

private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
private const val TAG = "MapViewFragment"

/**
 *
 * Provides a map fragment that uses a MapView object to keep a pure map fragment minimal.
 *
 * Most of the code for this class came from examples within
 * {@link https://github.com/googlemaps/android-samples}.
 *
 * Partial information of various degrees of helpfulness are at:
 * - https://developers.google.com/android/reference/com/google/android/gms/maps/MapView
 * - https://joekarl.github.io/2013/11/01/android-map-view-inside-fragment/
 *
 * See also the Google Tutorial at https://developers.google.com/maps/documentation/android-sdk/intro
 *
 */
open class MapViewFragment : Fragment() {

    protected lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView

    @Suppress("SameParameterValue")
    protected fun onCreateMapView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        mapLayoutResourceId: Int,
        mapViewResourceId: Int
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        val view = inflater.inflate(mapLayoutResourceId, container, false)
        mapView = view.findViewById(mapViewResourceId)
        mapView.onCreate(mapViewBundle)
        return view
    }

    fun onMapViewCreated(
        view: View, savedInstanceState: Bundle?,
        block: (GoogleMap) -> Unit
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Setting up map")
        mapView.getMapAsync { map ->
            Log.d(TAG, "Receiving map map fragment view")
            googleMap = map
            block.invoke(googleMap)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    /**
     * A utility function for setting bitmaps as map marker icons.
     * Defaults to 200dp square.
     */
    protected fun setMarkerIcon(
        marker: Marker,
        bitmap: Bitmap,
        dstWidth: Int = 200,
        dstHeight: Int = 200,
        filter: Boolean = false
    ) {
        val scaledBitmap =
            Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, filter)
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(scaledBitmap))
    }
}