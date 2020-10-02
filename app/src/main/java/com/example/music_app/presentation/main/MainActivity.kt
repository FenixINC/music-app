package com.example.music_app.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.music_app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

//    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment_nav_host)
        setupActionBar(navController)
        bottom_navigation.setupWithNavController(navController = navController)
    }

    private fun setupActionBar(navController: NavController) {
        setSupportActionBar(toolbar)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.home_fragment,
                R.id.search_fragment,
                R.id.favourite_fragment
            )
        )
        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )
    }
}