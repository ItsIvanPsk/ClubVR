package com.itsydev.clubvr


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.itsydev.clubvr.databinding.ActivityMainBinding
import androidx.fragment.app.activityViewModels


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    private var mNavDrawer: DrawerLayout? = null
    private val viewmodel: AppViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        mNavDrawer = binding.drawLayout
        val toggle = ActionBarDrawerToggle(
            this, mNavDrawer, binding.toolbar,
            R.string.open,
            R.string.close
        )
        mNavDrawer?.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> viewmodel.setDestination(0)
            R.id.nav_myclub -> viewmodel.setDestination(1)
            R.id.nav_profile -> viewmodel.setDestination(2)
            R.id.nav_logout -> viewmodel.setDestination(3)
            R.id.nav_settings -> viewmodel.setDestination(4)
            R.id.nav_accesibility -> viewmodel.setDestination(5)
        }

        mNavDrawer!!.closeDrawer(GravityCompat.START)

        return true
    }


}