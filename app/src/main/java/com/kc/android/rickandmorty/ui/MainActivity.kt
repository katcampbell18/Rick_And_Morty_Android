package com.kc.android.rickandmorty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.kc.android.rickandmorty.R
import com.kc.android.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainToolbar = binding.toolbar

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        setSupportActionBar(mainToolbar)
        setupActionBarWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupWithNavController(mainToolbar, navController, appBarConfiguration)
        mainToolbar.setNavigationOnClickListener {
            NavigationUI.navigateUp(navController, appBarConfiguration)
        }
    }
}