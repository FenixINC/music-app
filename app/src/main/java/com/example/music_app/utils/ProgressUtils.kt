package com.example.music_app.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.music_app.utils.ViewUtils.createProgressBar

object ProgressUtils {

    private var progressDialog: AlertDialog? = null

    fun setLoadingState(isLoading: Boolean, context: Context) {
        if (isLoading) {
            showLoading(context)
        } else {
            hideLoading()
        }
    }

    private fun showLoading(context: Context) = context?.let {
        progressDialog = createProgressBar(it)
        progressDialog?.show()
    }

    private fun hideLoading() {
        progressDialog?.dismiss()
        progressDialog = null
    }

}