package com.example.music_app.core.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val IMAGE_CORNER_RADIUS = 16
const val IMAGE_MARGIN = 0

fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(it)
            .centerInside()
            .into(this)
    }
}

fun ImageView.loadImageUrlWithLeftRoundedCorners(url: String?) {
    url?.let {
        Glide.with(context)
            .load(Uri.parse(it))
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        IMAGE_CORNER_RADIUS.dp,
                        IMAGE_MARGIN,
                        RoundedCornersTransformation.CornerType.LEFT
                    )
                )
            )
            .into(this)
    }
}