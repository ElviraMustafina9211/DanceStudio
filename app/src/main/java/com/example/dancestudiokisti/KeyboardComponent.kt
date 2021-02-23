package com.example.dancestudiokisti

import dagger.Component

@Component(modules = [KeyboardModule:: class])
interface KeyboardComponent  {
    fun keyboard () : Keyboard
}