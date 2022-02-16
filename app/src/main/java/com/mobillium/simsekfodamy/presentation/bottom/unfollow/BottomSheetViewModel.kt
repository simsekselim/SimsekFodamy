package com.mobillium.simsekfodamy.presentation.bottom.unfollow

import android.os.Bundle
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    private val user: UserRepository,
) : BaseViewModel() {

    private var userId: Int? = -1

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        userId = BottomSheetFragmentArgs.fromBundle(extras).userId
    }
    fun unfollowClick() {
        sendRequest(
            loading = false,
            request = { user.unfollowUser(userId!!) },
            success = {
                showMessage(R.string.unfollow)
                setExtras(UNFOLLOW, true)
                popBackStack()
            }
        )
    }

    companion object {
        private const val USER_ID = "userId"
        const val UNFOLLOW = "unfollow"
    }
}
