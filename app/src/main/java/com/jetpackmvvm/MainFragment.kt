package com.jetpackmvvm

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jetpackmvvm.core.BaseFragment
import com.jetpackmvvm.home.NewHomeFragment
import com.jetpackmvvm.house.NewFamilyFragment
import com.jetpackmvvm.message.MessageFragment
import com.jetpackmvvm.personal.MineFragment
import com.tenclouds.fluidbottomnavigation.FluidBottomNavigationItem
import com.tenclouds.fluidbottomnavigation.listener.OnTabSelectedListener
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
        vpFragmentMain.isUserInputEnabled = false
        vpFragmentMain.offscreenPageLimit = 1
        vpFragmentMain.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragmentList[position]

            override fun getItemCount() = fragmentList.size
        }
        fluidBottomNavigation.accentColor = ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        fluidBottomNavigation.backColor = ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        fluidBottomNavigation.textColor = ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark)
        fluidBottomNavigation.iconColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        fluidBottomNavigation.iconSelectedColor = ContextCompat.getColor(requireContext(), R.color.iconSelectedColor)

        fluidBottomNavigation.items =
            listOf(
                FluidBottomNavigationItem(
                    "设备",
                    ContextCompat.getDrawable(requireContext(), R.drawable.drawable_menu_device)),
                FluidBottomNavigationItem(
                    "家庭",
                    ContextCompat.getDrawable(requireContext(), R.drawable.drawable_menu_family)),
                FluidBottomNavigationItem(
                    "消息",
                    ContextCompat.getDrawable(requireContext(), R.drawable.drawable_menu_message)),
                FluidBottomNavigationItem(
                    "我的",
                    ContextCompat.getDrawable(requireContext(), R.drawable.drawable_menu_mine))
            )

        fluidBottomNavigation.onTabSelectedListener = object : OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                switchFragment(position)
            }
        }
    }

    private fun switchFragment(position: Int): Boolean {
        vpFragmentMain.setCurrentItem(position, false)
        return true
    }
}