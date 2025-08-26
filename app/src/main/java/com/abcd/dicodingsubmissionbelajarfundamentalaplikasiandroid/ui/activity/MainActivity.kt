package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ActivityMainBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.dashboard.UpcomingFragment
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.home.HomeFragment
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.notifications.FinishedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    setHomeFragment()
                    true
                }
                R.id.navigation_upcoming -> {
                    setUpcomingFragment()
                    true
                }
                R.id.navigation_finished -> {
                    setFinishedFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun setHomeFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
            .commit()
    }

    fun setUpcomingFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, UpcomingFragment())
            .commit()
    }

    fun setFinishedFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, FinishedFragment())
            .commit()
    }
}