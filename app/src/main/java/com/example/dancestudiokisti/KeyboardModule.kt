package com.example.dancestudiokisti

import dagger.Module
import dagger.Provides

@Module
class KeyboardModule {
    @Provides
    fun keyboard(): Keyboard {
        return KeyboardImpl()
    }
}