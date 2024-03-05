package com.example.vlcurlvideoplay

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.vlcurlvideoplay.databinding.ActivityMainBinding
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var libVlc: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var videoLayout: VLCVideoLayout

//    private val resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        playVideo(uri)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        libVlc = LibVLC(this)
        mediaPlayer = MediaPlayer(libVlc)
        videoLayout = findViewById(R.id.videoLayout)

        val button: Button = findViewById(R.id.openButton)
        button.setOnClickListener {
//            resultLauncher.launch("video/*")
            val videoUrl = "https://firebasestorage.googleapis.com/v0/b/hide-gallery-b0b01.appspot.com/o/KUf4be3hWKWBaxpHIoxC58EzeYr2%2Fvideos%2FVID-20231019-WA0010.mp4?alt=media&token=f876577a-889e-4cad-b2cd-96c72362c9e3"
            playVideo(videoUrl)
        }
    }

    override fun onStop() {
        super.onStop()

        mediaPlayer.stop()
        mediaPlayer.detachViews()
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer.release()
        libVlc.release()
    }

   /* private fun playVideo(uri: Uri?){
        if (uri === null) {
            return
        }
        val fd = contentResolver.openFileDescriptor(uri, "r")

        mediaPlayer.attachViews(videoLayout, null, false, false)

        val media = Media(libVlc, fd!!.fileDescriptor)
        media.setHWDecoderEnabled(true, false)
        media.addOption(":network-caching=600")

        mediaPlayer.media = media
        media.release()
        mediaPlayer.play()
    }*/

    private fun playVideo(videoUrl: String) {
        val media = Media(libVlc, Uri.parse(videoUrl))
        media.setHWDecoderEnabled(true, false)
        media.addOption(":network-caching=600")

        mediaPlayer.attachViews(videoLayout, null, false, false)
        mediaPlayer.media = media
        mediaPlayer.play()
    }

}



/*

https://firebasestorage.googleapis.com/v0/b/hide-gallery-b0b01.appspot.com/o/KUf4be3hWKWBaxpHIoxC58EzeYr2%2Fvideos%2FVID-20231019-WA0010.mp4?alt=media&token=f876577a-889e-4cad-b2cd-96c72362c9e3


https://docs.evostream.com/sample_content/assets/bun33s.mp4

*/
