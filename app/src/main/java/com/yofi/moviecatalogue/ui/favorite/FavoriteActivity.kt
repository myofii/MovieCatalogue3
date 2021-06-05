package com.yofi.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yofi.moviecatalogue.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite Movie Catalogue"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = Bundle()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}