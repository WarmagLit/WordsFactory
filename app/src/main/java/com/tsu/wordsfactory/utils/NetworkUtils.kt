package com.tsu.wordsfactory.utils

import android.app.DownloadManager
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.IOException

class NetworkUtils(val context: Context) {
    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    fun getDownloadManager(): DownloadManager {
        return context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

    fun playContentUri(uri: Uri) {
        var mMediaPlayer: MediaPlayer? = null
        mMediaPlayer = try {
            MediaPlayer().apply {
                setDataSource(context, uri)
                setAudioAttributes(AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
                )
                prepare()
                start()
            }
        } catch (exception: IOException) {
            Log.d("Tag","Crash player")
            mMediaPlayer?.release()
            null
        }
    }

    fun getDir(): Uri {
        val file = File.createTempFile("audio", ".mp3")

        //val file = File(context.cacheDir, "audio.mp3")
        return Uri.fromFile(file)
        //return context.getExternalFilesDir(null)!!.toUri()
    }
}