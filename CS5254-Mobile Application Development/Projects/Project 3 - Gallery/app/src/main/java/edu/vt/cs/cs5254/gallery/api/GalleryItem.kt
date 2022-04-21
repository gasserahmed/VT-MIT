package edu.vt.cs.cs5254.gallery.api

import android.graphics.drawable.Drawable
import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GalleryItem(
    var title: String = "",
    var id: String = "",
    var latitude: String = "",
    var longitude: String = "",
    @SerializedName("url_s") var url: String = "",
    @SerializedName("owner") var owner: String = "",
    @Expose(serialize = false, deserialize = false)
    var drawable: Drawable? = null // local cache for thumbnail
) {
    val photoPageUri: Uri
        get() {
            return Uri.parse("https://www.flickr.com/photos/")
                .buildUpon()
                .appendPath(owner)
                .appendPath(id)
                .build()
        }

}
