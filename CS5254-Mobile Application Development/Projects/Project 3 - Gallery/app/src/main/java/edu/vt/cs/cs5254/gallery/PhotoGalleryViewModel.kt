package edu.vt.cs.cs5254.gallery

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.vt.cs.cs5254.gallery.api.FlickrFetchr

class PhotoGalleryViewModel : ViewModel() {
    val galleryItemsLiveData = FlickrFetchr.galleryItemsLiveData

    fun loadPhotos() = FlickrFetchr.fetchPhotos()

    fun storeThumbnail(id: String, drawable: Drawable) {
        FlickrFetchr.storeThumbnail(id, drawable)
    }
}