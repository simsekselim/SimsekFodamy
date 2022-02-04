package com.mobillium.simsekfodamy.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.domain.model.Category
import com.mobillium.domain.model.Recipe
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.ItemCategoryBinding
import com.mobillium.simsekfodamy.presentation.homeflow.home.adapter.RecipeAdapter
import com.squareup.picasso.Picasso

class CategoryAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Category, CategoryAdapter.CategoryViewHolder>(CATEGORY_COMPARATOR) {

    var onChildItemClicked: ((Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                textCategoryName.text = category.name

                if (category.image != null)
                    Picasso.get()
                        .load(category.image!!.url)
                        .into(imageCategory)
                else
                    imageCategory.setImageResource(R.drawable.fork)

                textCategorySeeAll.setOnClickListener {
                    listener.onSeeAllClick(category)
                }
            }
            category.recipes?.let { setRecyclerView(it) }
        }

        private fun setRecyclerView(recipes: List<Recipe>) {
            binding.apply {
                recyclerCategoryRecipes.layoutManager =
                    LinearLayoutManager(
                        binding.root.context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                recyclerCategoryRecipes.hasFixedSize()
            }
            setAdapter(recipes)
        }

        private fun setAdapter(recipes: List<Recipe>) {
            val listener = object : RecipeAdapter.OnItemClickListener {
                override fun onItemClick(recipe: Recipe) {
                    listener.onRecipeClick(recipe)
                }
            }
            val adapter = CategoryRecipeAdapter(listener)
            binding.recyclerCategoryRecipes.adapter = adapter
            adapter.submitList(recipes)
        }
    }

    interface OnItemClickListener {
        fun onSeeAllClick(category: Category)
        fun onRecipeClick(recipe: Recipe)
    }

    companion object {
        private val CATEGORY_COMPARATOR = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem == newItem
        }
    }
}
