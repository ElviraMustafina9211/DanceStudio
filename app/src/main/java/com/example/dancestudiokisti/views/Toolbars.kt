package com.example.dancestudiokisti.views

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.example.dancestudiokisti.R

object Toolbars {
    fun enableBackButton(view: View, navController: NavController) {
        view.findViewById<Toolbar>(R.id.toolbar)?.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                navController.navigateUp()
            }
        }
    }
}