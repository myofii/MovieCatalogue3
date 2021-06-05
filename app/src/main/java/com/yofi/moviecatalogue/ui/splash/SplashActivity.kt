package com.yofi.moviecatalogue.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import com.yofi.moviecatalogue.databinding.ActivitySplashBinding
import com.yofi.moviecatalogue.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    companion object{
        const val delay: Long = 3000
    }

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(splashBinding.root)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, delay)
    }
}