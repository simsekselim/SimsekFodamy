package com.mobillium.simsekfodamy.utils

object Constants {

    // Base URL
    const val BASE_URL = "https://fodamy.mobillium.com/api/"

    //Action
    const val DIALOG_ACTION = "Dialog_Action"
    const val KEY_DELETE = "Key_Delete"

    // User Service
    const val LOGIN_URL = "auth/login"
    const val REGISTER_URL = "auth/register"
    const val FORGOT_URL = "auth/forgot"
    const val LOGOUT_URL = "auth/logout"
    const val USER = "user/{user_id}"
    const val FOLLOW_USER = "user/{followedId}/following"
    const val UNFOLLOW_USER = "user/{followedId}/following"

    // Recipe Service
    const val RECIPE = "recipe/{recipe_id}"
    const val LAST_ADD = "recipe/"
    const val EDITOR_CHOOSE = "editor-choices"
    const val CATEGORY = "category/{category_id}/recipe"
    const val CATEGORY_RECIPES = "category-recipes"
    const val RECIPE_ID = "recipe/{recipe_id}"
    const val RECIPE_COMMENTS = "recipe/{recipe_id}/comment"
    const val SEND_COMMENTS = "recipe/{recipe_id}/comment"
    const val LIKE_RECIPE = "recipe/{recipe_id}/like"
    const val DISLIKE_RECIPE = "recipe/{recipe_id}/like"
    const val EDIT_COMMENTS = "recipe/{recipe_id}/comment/{comment_id}"
    const val DELETE_COMMENTS = "recipe/{recipe_id}/comment/{comment_id}"

    // Presentation String
    const val FILL_REQUIRED_FIELDS = "Lütfen boşlukları doldurunuz."
    const val LOGGED_OUT = "Çıkış Yapıldı"
    const val LOGGED_OUT_ERROR = "Çıkış Yapılırken Sorun Oluştu"
    const val COMMENT = "YORUMLAR"
    const val DELETED = "Silindi"
    const val USER_NAME_FIELD = "Kullanıcı Adı Boş Bırakılamaz."
    const val EMAIL_FIELD = "Email Boş Bırakılamaz."
    const val PASSWORD_FIELD = "Şifre Boş Bırakılamaz."
}
