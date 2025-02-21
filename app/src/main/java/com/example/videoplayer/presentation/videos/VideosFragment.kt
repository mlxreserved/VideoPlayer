package com.example.videoplayer.presentation.videos

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.databinding.FragmentVideosBinding
import com.example.videoplayer.domain.model.videos.DataOfVideo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideosFragment: Fragment() {

    val videosViewModel: VideosViewModel by viewModels()

    private val videosState: StateFlow<VideosState> by lazy {
        videosViewModel.videosState
    }

    private lateinit var binding: FragmentVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)

        binding.videosRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                videosState.collect { state ->
                    when(state) {
                        is VideosState.Loading -> showLoading()
                        is VideosState.Loaded -> showLoadedVideos(state.videos.dataOfVideos)
                        is VideosState.Error -> showError()
                    }
                }
            }
        }
    }

    fun showLoadedVideos(videosList: List<DataOfVideo>) {
        val adapter = VideosAdapter(videosList)
        binding.videosRecyclerView.adapter = adapter
    }

    fun showLoading() {

    }

    fun showError() {

    }



}