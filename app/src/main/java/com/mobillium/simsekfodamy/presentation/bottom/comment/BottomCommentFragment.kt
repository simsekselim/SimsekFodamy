package com.mobillium.simsekfodamy.presentation.bottom.comment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.FragmentBottomCommentBinding
import com.mobillium.simsekfodamy.presentation.bottom.base.BottomBaseFragment
import com.mobillium.simsekfodamy.presentation.commentflow.comment.adapter.CommentsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomCommentFragment : BottomBaseFragment<BottomCommentViewModel,FragmentBottomCommentBinding>(
    R.layout.fragment_bottom_comment,
    BottomCommentViewModel::class.java
) {

//    var binding: FragmentBottomCommentBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentBottomCommentBinding.inflate(inflater, container, false)
//        return binding?.root
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
//        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)
//
//    private fun delete() {
//        setFragmentResult(
//            ACTION, bundleOf(DELETE to true)
//        )
//        findNavController().popBackStack()
//    }
//
//    private fun set() {
//        setFragmentResult(
//            ACTION, bundleOf(SET to true)
//        )
//        findNavController().popBackStack()
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding?.cancel?.setOnClickListener {
//            findNavController().popBackStack()
//        }
//        binding?.set?.setOnClickListener {
//            set()
//        }
//        binding?.delete?.setOnClickListener {
//            delete()
//        }
//    }
//    companion object {
//        const val ACTION = "action"
//        const val DELETE = "delete"
//        const val SET = "set"
//    }
}
