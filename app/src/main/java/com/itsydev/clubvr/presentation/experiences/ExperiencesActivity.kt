package com.itsydev.clubvr.presentation.experiences

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itsydev.clubvr.R
import com.itsydev.clubvr.databinding.ActivityExperiencesBinding
import com.itsydev.clubvr.databinding.ActivityMainBinding

class ExperiencesActivity : AppCompatActivity(){

    private lateinit var binding: ActivityExperiencesBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExperiencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupNavController()

        val navView: BottomNavigationView = binding.bottomNavigationView
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainMenu,
                R.id.myClubFragment,
                R.id.profileFragment,
                R.id.settingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(0).isEnabled = false
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        binding.bottomNavigationView.menu.getItem(3).isEnabled = false
        binding.bottomNavigationView.menu.getItem(4).isEnabled = false
        supportActionBar?.hide()


    }

    private fun setupListeners(){
        binding.experiencesFloatingButton.setOnClickListener {
            finish()
        }
    }

    // Setup NavController with the NavHostFragment
    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
    }

    // Setup the toolbar with navController and the config of the AppBar
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.experiencesFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }


}