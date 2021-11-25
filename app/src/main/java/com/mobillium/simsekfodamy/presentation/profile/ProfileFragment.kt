package com.mobillium.simsekfodamy.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(
    R.layout.fragment_profile,
    ProfileViewModel::class.java
) {

}