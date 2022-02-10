package com.airongomes.eventsapi.extension

import android.widget.ImageView
import androidx.core.net.toUri
import com.airongomes.eventsapi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imagePath: String) {
    val uri = imagePath.toUri().buildUpon().scheme("https").build()
    Glide.with(context)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image_error)
        )
        .into(this)
}