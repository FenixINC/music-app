package com.example.rick_morty_app.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.music_app.utils.dp
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val IMAGE_CORNER_RADIUS = 16
const val IMAGE_MARGIN = 0

fun ImageView.loadImageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .centerInside()
        .into(this)
}

fun ImageView.loadImageUrlWithLeftRoundedCorners(url: String?) {
    Glide.with(context)
        .load(Uri.parse(url))
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