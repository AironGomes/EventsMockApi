package com.airongomes.eventsapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.airongomes.eventsapi.R
import com.airongomes.eventsapi.databinding.BottomSheetCheckinBinding
import com.airongomes.eventsapi.domain.model.CheckIn
import com.airongomes.eventsapi.domain.model.Event
import com.airongomes.eventsapi.domain.remote.NetworkResult
import com.airongomes.eventsapi.viewModel.CheckInViewModel
import com.airongomes.eventsapi.util.extension.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInBottomSheet(val event: Event) : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetCheckinBinding
    private val viewModel: CheckInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetCheckinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCheckInResult()
        setupCheckIn()
    }

    private fun setupCheckIn() {
        binding.checkinButton.setOnClickListener {
            val name = binding.checkinName.editText?.text.toStringOrEmpty()
            val email = binding.checkinEmail.editText?.text.toStringOrEmpty()
            if (areValidFields(name, email)) {
                registerForEvent(name, email)
            }
        }

    }

    private fun areValidFields(name: String, email: String): Boolean =
        when {
            !name.hasValidField() -> {
                requireView().showMessage(getString(R.string.filling_required_error))
                false
            }
            !email.hasValidEmail() -> {
                requireView().showMessage(getString(R.string.invalid_email_error))
                false
            }
            else -> {
                true
            }

        }

    private fun registerForEvent(name: String, email: String) {
        val checkIn = CheckIn(event.id, name, email)
        viewModel.checkIn(checkIn)
        showLoading()
    }

    private fun showLoading() {
        binding.checkinButton.hide()
        binding.checkinProgress.show()
    }

    private fun fetchCheckInResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.checkIntResult.collectLatest {
                    when (it) {
                        is NetworkResult.Loading -> {}
                        is NetworkResult.Error -> {
                            binding.checkinProgress.hide()
                            requireView().showMessage(getString(R.string.checkin_error))
                            dismiss()
                        }
                        is NetworkResult.Success -> {
                            binding.checkinProgress.hide()
                            requireView().showMessage(getString(R.string.checkin_successful_msg))
                            dismiss()
                        }
                    }
                }

            }
        }

    }

    companion object {
        const val TAG = "CheckInBottomSheet"
    }
}