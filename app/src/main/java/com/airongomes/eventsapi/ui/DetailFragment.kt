package com.airongomes.eventsapi.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.airongomes.eventsapi.R
import com.airongomes.eventsapi.base.BaseFragment
import com.airongomes.eventsapi.databinding.FragmentDetailBinding
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.util.extension.loadImage
import com.airongomes.eventsapi.util.extension.toDateTime

class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        event = DetailFragmentArgs.fromBundle(requireArguments()).event
        setupView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.checkin -> {
                val bottomSheet = CheckInBottomSheet(event)
                bottomSheet.show(parentFragmentManager, CheckInBottomSheet.TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupView() {
        binding.titleDetail.text = event.title
        binding.descriptionDetail.text = event.description
        binding.imageDetail.loadImage(event.image)
        binding.dateDetail.text = event.date.toDateTime()
        binding.priceDetail.text = getString(R.string.event_price, event.price)
    }
}