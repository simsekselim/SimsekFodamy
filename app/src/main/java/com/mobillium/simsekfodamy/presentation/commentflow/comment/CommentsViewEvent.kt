package com.mobillium.simsekfodamy.presentation.commentflow.comment

sealed class CommentsViewEvent {
    object SendCommentSuccess : CommentsViewEvent()
    object DeleteCommentSuccess : CommentsViewEvent()
}
