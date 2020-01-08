package com.valuelab.photoapp.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName


data class UserPhotos(
    @SerializedName("albumId") val albumId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
) {
    /**
     * Binding adapter to download and sets image in image view using data binding
     */
    object DataBindingAdapter {
        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageByRes(imageView: ImageView, url: String?) {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }
}
