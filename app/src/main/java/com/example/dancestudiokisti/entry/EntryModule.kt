package com.example.dancestudiokisti.entry

import com.example.dancestudiokisti.TokenSaver
import dagger.Module
import dagger.Provides

@Module
class EntryModule {
    @Provides
    fun entryViewModelFactory(tokenSaver: TokenSaver): EntryViewModelFactory {
        return EntryViewModelFactory(tokenSaver)
    }
}