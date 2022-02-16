package com.mobillium.simsekfodamy.presentation.bottom.comment

import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.databinding.FragmentBottomCommentBinding
import com.mobillium.simsekfodamy.presentation.bottom.base.BottomBaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomCommentFragment :
    BottomBaseFragment<BottomCommentViewModel, FragmentBottomCommentBinding>(
        R.layout.fragment_bottom_comment,
        BottomCommentViewModel::class.java
    )
