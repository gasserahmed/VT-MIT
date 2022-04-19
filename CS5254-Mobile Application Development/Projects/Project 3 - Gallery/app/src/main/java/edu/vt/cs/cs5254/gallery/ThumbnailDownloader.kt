package edu.vt.cs.cs5254.gallery

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import edu.vt.cs.cs5254.gallery.api.FlickrFetchr
import java.util.concurrent.ConcurrentHashMap

private const val TAG = "ThumbnailDownloader"
private const val MESSAGE_DOWNLOAD = 0

class ThumbnailDownloader<in H : Any>(
    private val responseHandler: Handler,
    private val onThumbnailDownloaded: (H, Bitmap) -> Unit
) : HandlerThread(TAG) {
    val fragmentLifecycleObserver: DefaultLifecycleObserver =
        object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                Log.i(TAG, "Starting background thread")
                start()
                looper
            }

            override fun onDestroy(owner: LifecycleOwner) {
                Log.i(TAG, "Destroying background thread")
                quit()
            }
        }

    val viewLifecycleObserver: DefaultLifecycleObserver =
        object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                Log.i(TAG, "Clearing all requests from queue")
                requestHandler.removeMessages(MESSAGE_DOWNLOAD)
                requestMap.clear()
            }
        }

    private var hasQuit = false
    private lateinit var requestHandler: Handler
    private val requestMap = ConcurrentHashMap<H, String>()

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("HandlerLeak")
    override fun onLooperPrepared() {
        requestHandler = object : Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    val holder = msg.obj as H
                    Log.i(TAG, "Got a request for URL: ${requestMap[holder]}")
                    handleRequest(holder)
                }
            }
        }
    }

    override fun quit(): Boolean {
        hasQuit = true
        return super.quit()
    }

    fun queueThumbnail(holder: H, url: String) {
        Log.i(TAG, "Got a URL: $url")
        requestMap[holder] = url
        requestHandler.obtainMessage(MESSAGE_DOWNLOAD, holder)
            .sendToTarget()
    }

    private fun handleRequest(holder: H) {
        val url = requestMap[holder] ?: return
        val bitmap = FlickrFetchr.fetchPhoto(url) ?: return

        responseHandler.post(Runnable {
            if (requestMap[holder] != url || hasQuit) {
                return@Runnable
            }
            requestMap.remove(holder)
            onThumbnailDownloaded(holder, bitmap)
        })
    }
}