package com.zubut.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zubut.bottomview.Common

/**
 * @author Alejandro Barba on 11/29/18.
 */
class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val bottomNavigation = findViewById<Common>(R.id.bottom_navigation)

        bottomNavigation.addBadge()
    }
}