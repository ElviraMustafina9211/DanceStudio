package com.example.dancestudiokisti.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class RegistrationViewModelFactory (private val registrationRepository: RegistrationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrationViewModel(registrationRepository) as T
    }
}