package com.mobillium.simsekfodamy.presentation.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ItemCommentBinding
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.Recipe
import com.squareup.picasso.Picasso

class CommentsAdapter :
    PagingDataAdapter<Comment, CommentsAdapter.CommentViewHolder>(COMMENT_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }
    var onChildItemClicked: ((Recipe) -> Unit)? = null

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.apply {
                textUserName.text = comment.user.username
                textUserInfo.text =
                    String.format(
                        binding.root.context.getString(R.string.user_info),
                        comment.user.recipe_count,
                        comment.user.followed_count
                    )
                if (comment.user.image != null)
                    Picasso.get()
                        .load(comment.user.image.url)
                        .into(imageUser)
                else
                    imageUser.setImageResource(R.drawable.profile)
                textCommentBody.text = comment.text
                textCommentDifference.text = comment.difference
            }
        }
    }

    companion object {
        val COMMENT_COMPARATOR = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment) =
                oldItem == newItem
        }
    }
}
