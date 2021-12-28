package com.mobillium.simsekfodamy.presentation.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mobillium.simsekfodamy.R
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageLoader")
fun setImageLoader(iv: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .into(iv)
}

@BindingAdapter("app:profileLoader")
fun setProfileLoader(iv: ImageView, url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.profile)
        .into(iv)
}

@BindingAdapter("app:commentCount")
fun setCommentCount(tv: TextView, commentCount: Int) {
    tv.text = tv.context.resources.getString(R.string.comment, commentCount)
}

@BindingAdapter("app:likeCount")
fun setLikeCount(tv: TextView, likeCount: Int) {
    tv.text = tv.context.resources.getString(R.string.like, likeCount)
}

@BindingAdapter(value = ["app:recipeCount", "app:followerCount"], requireAll = false)
fun setRecipeCount(tv: TextView, recipeCount: Int, followerCount: Int) {
    tv.text = tv.context.resources.getString(R.string.user_info, recipeCount, followerCount)
}
