package com.example.infiniteerp.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.infiniteerp.R
import com.example.infiniteerp.databinding.ActivityHomeBinding
import com.example.infiniteerp.home.menu.MenuFragment
import com.example.infiniteerp.home.profile.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    val fragMenu: Fragment = MenuFragment()
    val fragProfile: Fragment = ProfileFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragMenu

    private lateinit var menuItem: MenuItem
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpBottomNav()

    }

    private fun setUpBottomNav() {

        fm.beginTransaction().add(R.id.nav_container, fragMenu).show(fragMenu).commit()
        fm.beginTransaction().add(R.id.nav_container, fragProfile).hide(fragProfile).commit()

        menu = binding.btnNavView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        binding.btnNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btn_home -> {
                    callFrag(0, fragMenu)
                }
                R.id.btn_profile -> {
                    callFrag(1, fragProfile)
                }
            }
            false
        }

    }

    private fun callFrag(i: Int, fragment: Fragment) {
        menuItem = menu.getItem(i)
        menuItem.isChecked = true

        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment

    }
}