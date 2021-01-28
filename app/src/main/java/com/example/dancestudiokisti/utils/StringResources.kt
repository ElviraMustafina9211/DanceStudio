package com.example.dancestudiokisti.utils

import android.content.Context
import androidx.annotation.StringRes

class StringResources(val context: Context) {
    fun get(@StringRes strRes: Int) = context.getString(strRes)
}