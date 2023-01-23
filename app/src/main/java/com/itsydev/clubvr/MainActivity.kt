package com.itsydev.clubvr


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.itsydev.clubvr.databinding.ActivityMainBinding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.itsydev.clubvr.presentation.main_menu.MainMenuFragment


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    private var mNavDrawer: DrawerLayout = findViewById(R.id.drawLayout)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mNavDrawer = binding.drawLayout

        setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(
            this, mNavDrawer, binding.toolbar,
            R.string.open,
            R.string.close
        )
        mNavDrawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(MainMenuFragment())
    }


    fun getDrawer(): DrawerLayout = mNavDrawer

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        MainActivity().getDrawer().closeDrawer(GravityCompat.START)
        return true
    }


}