package com.leoleo2.myokashi

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(url: String?) {
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}