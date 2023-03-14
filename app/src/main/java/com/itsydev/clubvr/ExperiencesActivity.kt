package com.itsydev.clubvr

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExperiencesActivity : AppCompatActivity(){

    lateinit var binding: ActivityExperiencesBinding
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
        supportActionBar?.hide()

    }

    private fun setupListeners(){
        binding.experiencesFloatingButton.setOnClickListener {
            finish()
        }
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    fun getActivityBinding(): ActivityExperiencesBinding {
        return binding
    }


}