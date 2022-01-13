package com.mobillium.simsekfodamy.presentation.detail

import android.content.res.ColorStateList
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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

@BindingAdapter("likeStyle")
fun isLike(imageView: ImageView, isLiked: Boolean) {
    if (isLiked) {
        imageView.imageTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    imageView.context,
                    R.color.red
                )
            )
    } else {
        imageView.imageTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    imageView.context,
                    R.color.cinder
                )
            )
    }
}

@BindingAdapter("buttonStyle")
fun isFollowing(button: Button, isFollowing: Boolean) {
    if (isFollowing) {
        button.text = button.context.resources.getString(R.string.following)
        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(button.context, R.color.red)
        )
        button.setTextColor(
            ContextCompat.getColor(
                button.context,
                R.color.cardview_light_background
            )
        )
    } else {
        button.text = button.context.resources.getString(R.string.follow_user)
        button.backgroundTintList =
            ColorStateList.valueOf(
                ContextCompat.getColor(button.context, R.color.cardview_light_background)
            )
        button.setTextColor(ContextCompat.getColor(button.context, R.color.red))
    }
}
