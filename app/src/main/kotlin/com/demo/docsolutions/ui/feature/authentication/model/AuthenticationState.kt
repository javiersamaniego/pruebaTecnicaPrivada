package com.demo.docsolutions.ui.feature.authentication.model

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
sealed class AuthenticationState {
    data object Idle: AuthenticationState()
    data object OnUserLogIn: AuthenticationState()
    data object Loading: AuthenticationState()
    data class OnError( val message: String): AuthenticationState()

    fun isLoading() = this == Loading
}