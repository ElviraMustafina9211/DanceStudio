package com.example.dancestudiokisti.login

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import com.example.dancestudiokisti.KeyboardComponent
import dagger.Component

@FragmentScope
@Component(modules = [LoginModule::class], dependencies = [AppComponent::class, KeyboardComponent::class])
interface LoginComponent {
    fun inject (loginFragment: LoginFragment)
}