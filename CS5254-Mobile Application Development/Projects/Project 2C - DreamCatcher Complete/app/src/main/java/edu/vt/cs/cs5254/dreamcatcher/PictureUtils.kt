package edu.vt.cs.cs5254.dreamcatcher

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class PictureUtils {
    companion object {
        fun isCameraAvailable(activity: Activity): Boolean {
            val packageManager: PackageManager = activity.packageManager
            return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
        }

        fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
            // Read in the dimensions of the image on disk
            var options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, options)
            val srcWidth = options.outWidth.toFloat()
            val srcHeight = options.outHeight.toFloat()
            // Figure out how much to scale down by
            var inSampleSize = 1
            if (srcHeight > destHeight || srcWidth > destWidth) {
                val heightScale = srcHeight / destHeight
                val widthScale = srcWidth / destWidth
                val sampleScale = if (heightScale > widthScale) {
                    heightScale
                } else {
                    widthScale
                }
                inSampleSize = Math.round(sampleScale)
            }
            options = BitmapFactory.Options()
            options.inSampleSize = inSampleSize
            // Read in and create final bitmap
            return BitmapFactory.decodeFile(path, options)
        }
    }
}