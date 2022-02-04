package com.mobillium.simsekfodamy.presentation.commentflow.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.domain.model.Comment
import com.mobillium.simsekfodamy.databinding.ItemCommentBinding

class CommentsAdapter :
    PagingDataAdapter<Comment, CommentsAdapter.CommentViewHolder>(COMMENT_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    var onChildItemClicked: ((Comment) -> Unit)? = null
    var itemClicked: ((Comment) -> Unit)? = null

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.textCommentBody.setOnLongClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    val currentItem = getItem(bindingAdapterPosition)
                    currentItem?.let {
                        itemClicked?.invoke(it)
                    }
                }
                false
            }
        }

        fun bind(comment: Comment) {
            binding.comment = comment
            onChildItemClicked?.invoke(comment)
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
