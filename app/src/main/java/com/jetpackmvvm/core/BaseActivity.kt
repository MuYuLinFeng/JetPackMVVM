package com.jetpackmvvm.core

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.jetpackmvvm.utils.StatusBarUtil

abstract class BaseActivity : AppCompatActivity(), UIBase {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.statusBarLightMode(getStatusBarDark(), this)
        setContentView(getLayoutResId())
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initView()
        initData()
    }

    protected open fun getStatusBarDark(): Boolean {
        return true
    }
}