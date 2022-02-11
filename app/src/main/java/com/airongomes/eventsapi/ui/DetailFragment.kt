package com.airongomes.eventsapi.ui

import android.os.Bundle
import android.view.View
import com.airongomes.eventsapi.base.BaseFragment
import com.airongomes.eventsapi.databinding.FragmentDetailBinding
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.extension.loadImage

class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val event = DetailFragmentArgs.fromBundle(requireArguments()).event
        setupView(event)
    }

    private fun setupView(event: Event) {
        binding.titleDetail.text = event.title
        binding.descriptionDetail.text = event.description
        binding.imageDetail.loadImage(event.image)
    }
}