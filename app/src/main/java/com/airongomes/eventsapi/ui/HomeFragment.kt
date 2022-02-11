package com.airongomes.eventsapi.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.airongomes.eventsapi.R
import com.airongomes.eventsapi.adapter.EventAdapter
import com.airongomes.eventsapi.base.BaseFragment
import com.airongomes.eventsapi.databinding.FragmentHomeBinding
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter: EventAdapter by lazy { EventAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchEvents()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.fetchEventList() }
        binding.rvEvents.adapter = adapter

        adapter.onClick = { openEventDetail(it) }
    }

    private fun fetchEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventList.collectLatest {
                    when (it) {
                        is NetworkResult.Loading -> {
                            binding.swipeRefreshLayout.isRefreshing = true
                        }
                        is NetworkResult.Success -> {
                            binding.swipeRefreshLayout.isRefreshing = false
                            adapter.submitList(it.data)
                        }
                        is NetworkResult.Error -> {
                            binding.swipeRefreshLayout.isRefreshing = false
                            showMessage(getString(R.string.error_load_message))
                        }
                    }
                }
            }
        }
    }

    private fun showMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    private fun openEventDetail(event: Event) {
        findNavController().navigate(
            HomeFragmentDirections.openEventDetails(event)
        )
    }
}