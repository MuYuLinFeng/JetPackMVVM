package com.jetpackmvvm.core

interface UIBase {
    fun getLayoutResId(): Int
    fun initView() {}
    fun initData() {}
}