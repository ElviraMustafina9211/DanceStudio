package com.example.dancestudiokisti.login

import com.example.dancestudiokisti.AppComponent
import com.example.dancestudiokisti.KeyboardComponent
import dagger.Component

@LoginScope
@Component(
    modules = [LoginModule::class],
    dependencies = [AppComponent::class, KeyboardComponent::class]
)
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun loginApi(): LoginApi
}