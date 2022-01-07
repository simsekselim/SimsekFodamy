package com.mobillium.simsekfodamy.presentation.commentflow.setcomment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentSetCommentBinding
import com.mobillium.simsekfodamy.presentation.commentflow.comment.CommentsViewEvent
import com.mobillium.simsekfodamy.utils.Constants.COMMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class SetCommentFragment() : BaseFragment<SetCommentViewModel, FragmentSetCommentBinding>(
    R.layout.fragment_set_comment,
    SetCommentViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.ivFodamy.isVisible = false
        binding.toolbar.ivLogout.isVisible = false
        binding.toolbar.tvFodamy.text = COMMENT

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.event.collect {
                when (it) {
                    SetCommentViewEvent.EditCommentSuccess -> {
                       setFragmentResult("action", bundleOf("refresh" to true))
                        viewModel.popBackStack()
                    }
                }

            }
        }
    }

}

