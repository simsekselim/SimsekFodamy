package com.mobillium.simsekfodamy.presentation.bottom

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
import com.mobillium.simsekfodamy.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    var binding: FragmentBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    private fun unfollowClick() {
        setFragmentResult(REQUEST_UNFOLLOW, bundleOf(UNFOLLOW to true))
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.cancel?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.unfollow?.setOnClickListener {
            unfollowClick()
        }
    }
    companion object {
        const val REQUEST_UNFOLLOW = "request_unfollow"
        const val UNFOLLOW = "unfollow"
    }
}
