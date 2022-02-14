package com.mobillium.simsekfodamy.presentation.bottom.unfollow

import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.FragmentBottomSheetBinding
import com.mobillium.simsekfodamy.presentation.bottom.base.BottomBaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment : BottomBaseFragment<BottomSheetViewModel, FragmentBottomSheetBinding>(
    R.layout.fragment_bottom_sheet,
    BottomSheetViewModel::class.java
)
