package com.mobillium.simsekfodamy.presentation.loginflow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.simsekfodamy.R

class ViewPagerAdapter(
    val title: MutableList<String>,
    val details: List<String>,
    val images: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val itemDetails: TextView = itemView.findViewById(R.id.tvAbout)
        val itemImage: ImageView = itemView.findViewById(R.id.tvImage)

        init {
            itemImage.setOnClickListener { v: View ->

                val position = adapterPosition
                Toast.makeText(itemView.context, "You Clicked On Item ${position + 1}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Pager2ViewHolder {

        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {

        holder.itemTitle.text = title[position].toString()
        holder.itemDetails.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {

        return title.size
    }
}
