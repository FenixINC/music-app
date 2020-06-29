package com.example.music_app.core.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import com.example.music_app.R

private const val TOAST_BOTTOM_PADDING = 80

fun showSimpleErrorDialog(
    context: Context?,
    title: String?,
    message: String?,
    @ColorRes backgroundColor: Int? = null,
    @ColorRes textColor: Int? = null
) {
    val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(R.layout.view_error, null, false)
    val errorIcon: ImageView = layout.findViewById(R.id.image_error)
    val backgroundView: ViewGroup = layout.findViewById(R.id.layout_background)
    val titleView: TextView = layout.findViewById(R.id.text_error_title)
    val messageView: TextView = layout.findViewById(R.id.text_error_message)

    backgroundColor?.let {
        backgroundView.backgroundTintList = context.getColorStateList(it)
    }
    textColor?.let {
        errorIcon.colorFilter = PorterDuffColorFilter(context.getColor(it), PorterDuff.Mode.SRC_IN)
    }
    titleView.text = title
    textColor?.let { titleView.setTextColor(context.getColor(textColor)) }
    messageView.text = message
    textColor?.let { messageView.setTextColor(context.getColor(textColor)) }

    with(Toast(context)) {
        setGravity(Gravity.BOTTOM, 0, TOAST_BOTTOM_PADDING)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}

fun createProgressBar(context: Context) = AlertDialog.Builder(context)
    .setView(R.layout.layout_progress)
    .setCancelable(false)
    .create()

fun createTextChangedListener(textChanged: (String) -> Unit) = object : TextWatcher {

    override fun afterTextChanged(s: Editable?) = textChanged(s.toString())

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}