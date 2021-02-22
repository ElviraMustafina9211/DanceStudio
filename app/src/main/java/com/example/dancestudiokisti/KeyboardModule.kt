package com.example.dancestudiokisti

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class KeyboardModule {

    @Provides
    fun keyboard(): Keyboard {
        return KeyboardImpl()
    }
}