package com.example.videoplayer.presentation.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentVideosBinding
import com.example.videoplayer.domain.model.video.Video
import com.example.videoplayer.presentation.videos.state.VideosState
import com.example.videoplayer.presentation.videos.recyclerView.VideosAdapter
import com.example.videoplayer.util.Constant.SECURED_URL_START
import com.example.videoplayer.util.Constant.UNSECURED_URL_START
import com.example.videoplayer.util.Constant.VIDEO_URI_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideosFragment: Fragment() {

    val videosViewModel: VideosViewModel by activityViewModels()

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

        val navController = findNavController()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                videosState.collect { state ->
                    when(state) {
                        is VideosState.Loading -> showLoading()
                        is VideosState.Loaded -> {
                            showLoadedVideos(videosList = state.videos, navController)
                        }
                        is VideosState.Error -> showError(errorMessage = state.errorMessage)
                    }
                }
            }
        }

        binding.errorRefreshButton.setOnClickListener {
            refreshVideosListAfterError()
        }

        binding.swiperefresh.setOnRefreshListener {
            updateVideosList()
        }
    }

    private fun updateVideosList() {
        binding.swiperefresh.isRefreshing = false
        viewLifecycleOwner.lifecycleScope.launch {
            videosViewModel.getVideosWithStateHandling()
        }
    }

    private fun refreshVideosListAfterError() {
        viewLifecycleOwner.lifecycleScope.launch {
            videosViewModel.getVideosWithStateHandling()
        }
    }

    private fun showLoadedVideos(videosList: List<Video>, navController: NavController) {
        val adapter = VideosAdapter(videosList)
        hideErrorAndLoadingLayouts()
        setOnClickListenerToAdapter(adapter, navController)
        binding.videosRecyclerView.visibility = View.VISIBLE
        binding.videosRecyclerView.adapter = adapter
    }

    private fun showLoading() {
        binding.loadingLayout.visibility = View.VISIBLE
        hideErrorAndLoadedLayouts()
    }

    private fun showError(errorMessage: String) {
        binding.errorLayout.visibility = View.VISIBLE
        binding.errorMessage.text = errorMessage
        hideLoadedAndLoadingLayouts()
    }

    private fun setOnClickListenerToAdapter(adapter: VideosAdapter, navController: NavController) {
        adapter.setOnClickListener(object : VideosAdapter.OnClickListener {
            override fun onClick(position: Int, item: Video) {
                val uriMediaItem = item.asset.url

                val bundle = bundleOf(
                    VIDEO_URI_KEY to uriMediaItem
                )
                navController.navigate(R.id.action_videosFragment_to_selectedVideoFragment, bundle)
            }
        })
    }

    private fun hideErrorAndLoadingLayouts() {
        binding.errorLayout.visibility = View.GONE
        binding.loadingLayout.visibility = View.GONE
    }

    private fun hideErrorAndLoadedLayouts() {
        binding.errorLayout.visibility = View.GONE
        binding.videosRecyclerView.visibility = View.GONE
    }

    private fun hideLoadedAndLoadingLayouts() {
        binding.loadingLayout.visibility = View.GONE
        binding.videosRecyclerView.visibility = View.GONE
    }


}