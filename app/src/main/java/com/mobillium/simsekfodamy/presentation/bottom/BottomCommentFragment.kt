package com.mobillium.simsekfodamy.presentation.bottom

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.mobillium.simsekfodamy.databinding.FragmentBottomSheetBinding
import com.mobillium.simsekfodamy.databinding.FragmentCommentsBinding


class BottomCommentFragment : BottomSheetDialogFragment() {
    var binding: FragmentBottomCommentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomCommentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    private fun delete() {
        setFragmentResult(
            "action", bundleOf("delete" to true)
        )
        findNavController().popBackStack()
    }

    private fun set() {
        setFragmentResult(
            "action", bundleOf("set" to true)
        )
        findNavController().popBackStack()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.cancel?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.set?.setOnClickListener {
            set()
        }
        binding?.delete?.setOnClickListener {
            delete()

        }


    }
}
