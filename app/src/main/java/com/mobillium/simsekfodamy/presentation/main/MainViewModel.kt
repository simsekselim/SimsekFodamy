package com.mobillium.simsekfodamy.presentation.main

import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val repository: RecipeRepository) : BaseViewModel() {
}