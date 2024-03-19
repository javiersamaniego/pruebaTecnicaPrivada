package com.demo.docsolutions.ui.feature.user.model

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
sealed class UserCreationState {

    data object Idle : UserCreationState()
    data object Loading : UserCreationState()
    data class OnError(val message: String) : UserCreationState()
    data object OnCreated : UserCreationState()
}