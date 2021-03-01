package com.example.dancestudiokisti.registration

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.FragmentScope
import com.example.dancestudiokisti.KeyboardComponent
import com.example.dancestudiokisti.login.LoginComponent
import dagger.Component

@FragmentScope
@Component(
    modules = [RegistrationModule::class],
    dependencies = [AppComponent::class, KeyboardComponent::class, LoginComponent::class]
)
interface RegistrationComponent {
    fun inject(registrationFragment: RegistrationFragment)
}