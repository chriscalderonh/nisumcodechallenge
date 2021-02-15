package com.chriscalderonh.nisumcodechallenge.search.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.chriscalderonh.nisumcodechallenge.R
import com.chriscalderonh.nisumcodechallenge.search.presentation.di.DaggerSearchComponent

class SearchActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val listener = NavController.OnDestinationChangedListener { _, _, _ ->
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        DaggerSearchComponent.builder()
            .build()
            .inject(this)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        navController = Navigation.findNavController(this, R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(findViewById(R.id.tbSearch))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}