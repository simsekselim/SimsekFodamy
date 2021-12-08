package com.mobillium.simsekfodamy.presentation.category


import androidx.fragment.app.Fragment
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCategoryBinding


class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>(
    R.layout.fragment_category,
    CategoryViewModel::class.java
) {
}