package edu.vt.cs.cs5254.gallery

import androidx.lifecycle.ViewModel
import edu.vt.cs.cs5254.gallery.api.FlickrFetchr

class GalleryViewModel : ViewModel() {
    val galleryItemLiveData = FlickrFetchr().fetchPhotos()
}