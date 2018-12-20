package com.zubut.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zubut.bottomview.Common

/**
 * @author Alejandro Barba on 11/29/18.
 */
class MainActivity : AppCompatActivity() {

    lateinit var mBottomNavigationView: Common

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        setupWidgets()
    }

    private fun setupWidgets() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation)

        mBottomNavigationView.apply {
            addBadge()

            runOnUiThread {
                setOnNavigationItemSelectedListener {
                    if (it.itemId == R.id.nav_1) {
                        removeBadge()
                    } else {
                        addBadge()
                    }
                    true
                }
            }
        }

    }
}