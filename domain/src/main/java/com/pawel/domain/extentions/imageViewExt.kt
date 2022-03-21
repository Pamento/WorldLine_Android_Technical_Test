package com.pawel.domain.extentions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.RequestManager

fun ImageView.loadSimpleImg(url: String, fallbackImage: Drawable, glide: RequestManager) {
    glide.load(url).error(fallbackImage).into(this)
}