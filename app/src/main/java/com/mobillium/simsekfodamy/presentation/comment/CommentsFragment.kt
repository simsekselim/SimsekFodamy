package com.mobillium.simsekfodamy.presentation.comment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentCommentsBinding
import com.mobillium.simsekfodamy.presentation.comment.adapter.CommentsAdapter
import com.mobillium.simsekfodamy.utils.showIme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CommentsFragment() :
    BaseFragment<CommentsViewModel, FragmentCommentsBinding>(
        R.layout.fragment_comments,
        CommentsViewModel::class.java
    ) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.toolbar.ivFodamy.isVisible = false
        binding.toolbar.ivLogout.isVisible = false
        binding.toolbar.tvFodamy.text = "Yorumlar"
        binding.toolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.toolbar.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.navigate.observe(viewLifecycleOwner,{
            findNavController().navigate(R.id.loginWarningDialog)
        })
        val adapter = CommentsAdapter()


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
                        updateAdapter()
                        viewModel.commentText.value = ""
                    }
                }
            }
        }
    }

    private fun updateAdapter() {
        val adapter = binding.recycler.adapter as PagingDataAdapter<*, *>
        adapter.refresh()
    }

}








