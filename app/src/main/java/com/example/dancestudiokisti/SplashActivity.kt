package com.example.dancestudiokisti

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dancestudiokisti.sectionsList.SectionsListActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SectionsListActivity::class.java)
        startActivity(intent)
        finish()
    }
}