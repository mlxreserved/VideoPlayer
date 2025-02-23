package com.example.videoplayer.presentation.selectedVideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayer.databinding.FragmentSelectedVideoBinding
import com.example.videoplayer.domain.customError.PlayVideoError
import com.example.videoplayer.util.Constants.PLAYER_CURRENT_POS_KEY
import com.example.videoplayer.util.Constants.PLAYER_IS_READY_KEY
import com.example.videoplayer.util.Constants.VIDEO_URI_KEY
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.max

@AndroidEntryPoint
class SelectedVideoFragment: Fragment() {

    val selectedVideoViewModel: SelectedVideoViewModel by viewModels()

    private lateinit var binding: FragmentSelectedVideoBinding
    private var exoPlayer: ExoPlayer? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(PLAYER_CURRENT_POS_KEY, max(0L, exoPlayer?.currentPosition ?: 0L))
        outState.putBoolean(PLAYER_IS_READY_KEY, exoPlayer?.playWhenReady ?: false)
    }

    @OptIn(UnstableApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedVideoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoUri = arguments?.getString(VIDEO_URI_KEY)

        if(videoUri != null && !selectedVideoViewModel.isVideoPlaying()) {
            selectedVideoViewModel.playVideo(videoUri)
        }

        errorHandle()

        exoPlayer = selectedVideoViewModel.getPlayer()
        if(exoPlayer != null) {
            resumePlaybackFromSavedState(savedInstanceState)
            binding.playerView.player = exoPlayer
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.playerView.player = null
    }

    private fun errorHandle() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                selectedVideoViewModel.errors.collect { error ->
                    when (error) {
                        is PlayVideoError.NetworkError -> showError("Network error occurred")
                        is PlayVideoError.TimeoutError -> showError("Request timed out")
                        is PlayVideoError.HttpError -> showError("HTTP error: ${error.code}, ${error.message}")
                        is PlayVideoError.DecodingError -> showError("Failed to decode video")
                        is PlayVideoError.UnknownError -> showError("Unknown error: ${error.cause?.message}")
                        null -> {}
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun resumePlaybackFromSavedState(inState: Bundle?) {
        if(inState != null) {
            exoPlayer?.playWhenReady = inState.getBoolean(PLAYER_IS_READY_KEY)
            exoPlayer?.seekTo(inState.getLong(PLAYER_CURRENT_POS_KEY))
        }
    }
}