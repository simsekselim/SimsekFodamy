package com.mobillium.simsekfodamy.presentation.commentflow.comment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCommentsBinding
import com.mobillium.simsekfodamy.presentation.commentflow.comment.adapter.CommentsAdapter
import com.mobillium.simsekfodamy.utils.CommentPagingFactory
import com.mobillium.simsekfodamy.utils.Constants.COMMENT
import com.mobillium.simsekfodamy.utils.showIme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentsFragment() :
    BaseFragment<CommentsViewModel, FragmentCommentsBinding>(
        R.layout.fragment_comments,
        CommentsViewModel::class.java
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CommentsAdapter()

        adapter.itemClicked = {

            viewModel.toBottomSheet()
            viewModel.comment.value = it
        }

        binding.toolbar.ivFodamy.isVisible = false
        binding.toolbar.ivLogout.isVisible = false
        binding.toolbar.tvFodamy.text = COMMENT




        setFragmentResultListener("action") { requestKey, bundle ->
            if (bundle.getBoolean("set", false)) {

                viewModel.toEdit()

            } else if (bundle.getBoolean("delete", false)) {

                viewModel.deleteComment()

            } else if(bundle.getBoolean("refresh",false)){
                lifecycleScope.launch {
                    adapter.refresh()


                }


            }
        }


        val linearLayoutManager = LinearLayoutManager(requireContext())

        (activity as AppCompatActivity).showIme()
        binding.comment.requestFocus()

        binding.apply {
            recycler.layoutManager = linearLayoutManager
            recycler.setHasFixedSize(false)
            recycler.adapter = adapter
        }

        viewModel.comments.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)

        }

        binding.share.setOnClickListener {
            viewModel.sendComment()
            requireView().clearFocus()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.event.collect {
                when (it) {
                    CommentsViewEvent.SendCommentSuccess -> {
                        adapter.refresh()
                        viewModel.commentText.value = ""
                    }
                    CommentsViewEvent.DeleteCommentSuccess -> adapter.refresh()
                }
            }
        }
    }
}



