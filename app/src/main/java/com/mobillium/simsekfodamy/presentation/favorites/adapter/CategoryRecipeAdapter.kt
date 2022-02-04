package com.mobillium.simsekfodamy.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.domain.model.Recipe
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ItemCategoryRecipeBinding
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import com.squareup.picasso.Picasso

class CategoryRecipeAdapter(private val listener: RecipeAdapter.OnItemClickListener) :
    ListAdapter<Recipe, CategoryRecipeAdapter.CategoryRecipeViewHolder>(CATEGORY_RECIPE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecipeViewHolder {
        val binding =
            ItemCategoryRecipeBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent, false
            )
        return CategoryRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryRecipeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class CategoryRecipeViewHolder(private val binding: ItemCategoryRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val item = getItem(pos)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(recipe: Recipe) {
            binding.apply {
                textCategoryRecipeName.text = recipe.title
                textCategoryRecipeInfo.text =
                    String.format(
                        binding.root.context.getString(R.string.recipe_info),
                        recipe.comment_count,
                        recipe.like_count
                    )
                textUserName.text = recipe.user.username

                if (recipe.user.image != null)
                    Picasso.get()
                        .load(recipe.user.image!!.url)
                        .placeholder(R.drawable.user)
                        .into(imageUserProfile)
                else
                    imageUserProfile.setImageResource(R.drawable.profile)

                if (recipe.images != null)
                    Picasso.get()
                        .load(recipe.images[0].url)
                        .into(imageCategoryRecipe)
            }
        }
    }

    companion object {
        private val CATEGORY_RECIPE_COMPARATOR = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
                oldItem == newItem
        }
    }
}
