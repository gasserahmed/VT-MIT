package edu.vt.cs.cs5254.gallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


class PictureUtil private constructor() {

  companion object {

    private val placeholders = mutableMapOf<Context, Drawable>()

    fun placeholder(context: Context): Drawable {
      if (placeholders[context] == null) {
        placeholders[context] = ContextCompat.getDrawable(
          context,
          R.drawable.image_loading
        ) ?: ColorDrawable()
      }

      return placeholders[context]!!
    }


    fun drawableToBitmap(drawable: Drawable): Bitmap {
      if (drawable is BitmapDrawable) {
        if (drawable.bitmap != null) {
          return drawable.bitmap
        }
      }
      val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(
          1,
          1,
          Bitmap.Config.ARGB_8888
        ) // Single color bitmap will be created of 1x1 pixel
      } else {
        Bitmap.createBitmap(
          drawable.intrinsicWidth,
          drawable.intrinsicHeight,
          Bitmap.Config.ARGB_8888
        )
      }
      val canvas = Canvas(bitmap)
      drawable.setBounds(0, 0, canvas.width, canvas.height)
      drawable.draw(canvas)
      return bitmap
    }
  }
}