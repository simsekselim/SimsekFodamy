package com.mobillium.simsekfodamy.presentation.images.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.model.Image
import com.squareup.picasso.Picasso

class ImageAdapter(
    val images: List<Image>
) : RecyclerView.Adapter<ImageAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemImage: ImageView = itemView.findViewById(R.id.image_recipe)

        fun bindData(item: Image) {

            Picasso.get()
                .load(item.url)
                .into(itemImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder {

        return PagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {

        holder.bindData(images[position])
    }

    override fun getItemCount(): Int {

        return images.size
    }
}
