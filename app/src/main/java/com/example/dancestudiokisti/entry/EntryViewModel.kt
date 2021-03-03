package com.example.dancestudiokisti.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dancestudiokisti.TokenSaver


class EntryViewModel(private var tokenSaver: TokenSaver) : ViewModel() {

    private val _haveToken: MutableLiveData<Boolean> = MutableLiveData<Boolean>(tokenSaver.token != null)
    val haveToken: LiveData<Boolean> = _haveToken
}
