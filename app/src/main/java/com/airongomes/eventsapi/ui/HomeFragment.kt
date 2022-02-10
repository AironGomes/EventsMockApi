package com.airongomes.eventsapi.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.airongomes.eventsapi.adapter.EventAdapter
import com.airongomes.eventsapi.base.BaseFragment
import com.airongomes.eventsapi.databinding.FragmentHomeBinding
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.viewModel.HomeViewModel
import kotlinx.coroutines.flow.catch
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
        binding.rvEvents.adapter = adapter

        adapter.onClick = {
            openEventDetail(it)
        }
    }

    private fun openEventDetail(eventId: Int) {//TODO
        findNavController().navigate(
            HomeFragmentDirections.openEventDetails()
        )
    }

    private fun fetchEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchEventList().catch { error ->
                showErrorMessage("Unable to load data")
            }.collectLatest {
                when (it) {
                    is NetworkResult.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is NetworkResult.Success -> {
                        binding.progressbar.visibility = View.GONE
                        adapter.submitList(it.data)
                        Log.i("HomeFragment", "Events: ${it.data.toString()}")
                    }
                    is NetworkResult.Error -> {
                        binding.progressbar.visibility = View.GONE
                        showErrorMessage("Unable to load data")
                    }
                }
            }
        }
    }

    private fun showErrorMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}