package com.mobillium.simsekfodamy.presentation.commentflow.editcomment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentEditCommentBinding
import com.mobillium.simsekfodamy.utils.Constants.COMMENT
import com.mobillium.simsekfodamy.utils.showIme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCommentFragment() : BaseFragment<EditCommentViewModel, FragmentEditCommentBinding>(
    R.layout.fragment_edit_comment,
    EditCommentViewModel::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.ivLogout.isVisible = false
            toolbar.ivFodamy.isVisible = false
            toolbar.tvFodamy.text = COMMENT
        }
        (activity as AppCompatActivity).showIme()
        binding.comment.requestFocus()
    }

    companion object {
        const val ACTION = "action"
        const val REFRESH = "refresh"
    }
}
