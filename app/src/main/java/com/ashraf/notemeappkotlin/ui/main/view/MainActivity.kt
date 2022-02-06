package com.ashraf.notemeappkotlin.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ashraf.notemeappkotlin.R

class MainActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_TIME:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, HomeActivity::class.java)
            startActivity(mainIntent)
            this.finish()
        }, SPLASH_DISPLAY_TIME)
    }
}