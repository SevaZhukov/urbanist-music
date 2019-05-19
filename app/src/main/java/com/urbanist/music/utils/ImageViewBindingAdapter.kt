package com.urbanist.music.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageBitmap")
fun loadImage(iv: ImageView, bitmap: Bitmap?) {
	iv.setImageBitmap(bitmap)
}

@BindingAdapter("imageResource")
fun setImageViewResource(imageView: ImageView, resource: Int) {
	imageView.setImageResource(resource)
}