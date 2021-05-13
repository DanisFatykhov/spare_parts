package com.example.spare_parts.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.spare_parts.R
import com.example.spare_parts.databinding.ActivityMainBinding
import com.example.spare_parts.screens.editvehicle.EditListActivity
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.setupAuthGuard
import com.example.spare_parts.screens.editprofile.EditProfileActivity
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mViewModel: MainViewModel
    private lateinit var rootElement: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        setupAuthGuard {
            mViewModel = initViewModel()
        }

        init()

        with(rootElement.mainContent) {
            rentBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, RentActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
            placeBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, AddVehicleActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)
            }

            applicationBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, ListApplicationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }
    }


    companion object {
        const val TAG = "MainActivity"
    }

    private fun init() {
        val toggle = ActionBarDrawerToggle(this, rootElement.drawerLayout, rootElement.mainContent.toolbar, R.string.open_drawer, R.string.close_drawer)
        rootElement.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        rootElement.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.id_profile -> {
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                overridePendingTransition(0, 0)
            }

            R.id.id_my_vehicle -> {
                val intent = Intent(this, EditListActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                overridePendingTransition(0, 0)
            }

            R.id.id_exit -> {
                mViewModel.signOut()
            }
        }

        rootElement.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (rootElement.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            rootElement.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}