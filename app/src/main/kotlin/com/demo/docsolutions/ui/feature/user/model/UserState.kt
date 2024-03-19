package com.demo.docsolutions.ui.feature.user.model

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
sealed class UserState {

    data object Idle : UserState()
    data object Loading : UserState()
    data class OnError(val message: String) : UserState()
    data class OnSearchUser(val users: List<UserUI>) : UserState()
}