package com.example.music_app.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.music_app.R
import com.example.music_app.presentation.base.BaseFragment

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @IdRes
    protected val fragmentHostId = R.id.fragment_nav_host

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, fragmentHostId).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val view = findViewById<View>(fragmentHostId)
        if (view is BaseFragment/*<*>*/) {
//            view.onBackPressed()
        }
    }
}