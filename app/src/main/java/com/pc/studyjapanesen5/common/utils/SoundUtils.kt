package com.pc.studyjapanesen5.common.utils

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object SoundUtils {

    private lateinit var mediaPlayer: MediaPlayer

    fun getFileMp3FromAsset(context: Context, assetFile: String) {
        val assetManager = context.assets
        val assetFilePath = "mp3/$assetFile"

        val inputStream: InputStream = assetManager.open(assetFilePath)

        val tempFile = File.createTempFile("temp", null, context.cacheDir)
        tempFile.deleteOnExit()

        inputStream.use { input ->
            FileOutputStream(tempFile).use { output ->
                input.copyTo(output)
            }
        }

        val uri = Uri.fromFile(tempFile)

        playMp3(context, uri)
    }

    private fun playMp3(context: Context, uri: Uri?) {
        mediaPlayer = MediaPlayer.create(context, uri)
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
        mediaPlayer.start()
    }

    fun stopMp3() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
