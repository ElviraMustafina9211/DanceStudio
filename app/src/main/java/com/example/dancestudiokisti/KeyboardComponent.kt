package com.example.dancestudiokisti

import dagger.Component
import javax.inject.Singleton

@Component(modules = [KeyboardModule:: class])
interface KeyboardComponent  {
    fun keyboard () : Keyboard
}