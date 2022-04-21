package edu.vt.cs.cs5254.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import edu.vt.cs.cs5254.gallery.databinding.FragmentGalleryBinding

class PhotoMapFragment : MapViewFragment() {
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
    }
}