package com.jetpackmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.jetpackmvvm.core.BaseFragment
import com.jetpackmvvm.home.NewHomeFragment
import com.jetpackmvvm.house.NewFamilyFragment
import com.jetpackmvvm.message.MessageFragment
import com.jetpackmvvm.personal.MineFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    private val fragmentList = arrayListOf<Fragment>()
    private val newHomeFragment by lazy { NewHomeFragment() }
    private val newFamilyFragment by lazy { NewFamilyFragment() }
    private val messageFragment by lazy { MessageFragment() }
    private val mineFragment by lazy { MineFragment() }

    init {
        fragmentList.run {
            add(newHomeFragment)
            add(newFamilyFragment)
            add(messageFragment)
            add(mineFragment)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_main

    override fun initView() {
        super.initView()
        navMainMenu.itemTextAppearanceActive = R.style.bottom_normal_text
        navMainMenu.itemTextAppearanceInactive = R.style.bottom_normal_text
        vpFragmentMain.isUserInputEnabled = false
        vpFragmentMain.offscreenPageLimit = 1
        vpFragmentMain.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragmentList[position]

            override fun getItemCount() = fragmentList.size
        }
        navMainMenu.setOnNavigationItemSelectedListener(onNavigationItemSelected)
    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.menu_device -> {
                switchFragment(0)
            }
            R.id.menu_family -> {
                switchFragment(1)
            }
            R.id.menu_message -> {
                switchFragment(2)
            }
            R.id.menu_mine -> {
                switchFragment(3)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        vpFragmentMain.setCurrentItem(position, false)
        return true
    }
}