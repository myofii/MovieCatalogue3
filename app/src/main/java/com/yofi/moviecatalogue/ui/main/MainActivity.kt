package com.yofi.moviecatalogue.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yofi.moviecatalogue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Movie Catalogue"

        val bundle = Bundle()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }
}