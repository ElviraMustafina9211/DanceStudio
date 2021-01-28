package com.example.dancestudiokisti.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dancestudiokisti.utils.StringResources

class LoginViewModelFactory(
    private val loginRepository: LoginRepository,
    private val stringResources: StringResources
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository, stringResources) as T
    }
}