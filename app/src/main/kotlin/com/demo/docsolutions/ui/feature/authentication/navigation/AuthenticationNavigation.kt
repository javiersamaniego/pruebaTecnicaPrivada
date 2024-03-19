package com.demo.docsolutions.ui.feature.authentication.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.docsolutions.ui.feature.authentication.AuthenticationRoute
import com.demo.docsolutions.ui.feature.authentication.AuthenticationViewModel
import com.demo.docsolutions.ui.feature.authentication.model.AuthenticationState

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
object AuthenticationNavigation {

    const val AUTHENTICATION_LOGIN_ROUTE = "authentication_login"

    fun navigateAuthenticationLogin() = AUTHENTICATION_LOGIN_ROUTE

    fun authenticationLogin(
        navGraphBuilder: NavGraphBuilder,
        onNavigateToUsers: () -> Unit
    ) {
        navGraphBuilder.composable(
            route = AUTHENTICATION_LOGIN_ROUTE
        ) {

            val viewModel: AuthenticationViewModel = hiltViewModel()
            val authenticationState: AuthenticationState by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(authenticationState) {
                if (authenticationState is AuthenticationState.OnUserLogIn)
                    onNavigateToUsers()
            }

            AuthenticationRoute(
                authenticationState = authenticationState,
                username = viewModel.username,
                password = viewModel.password,
                onUpdateUserName = viewModel::updateUserName,
                onUpdatePassword = viewModel::updatePassword,
                onLogin = viewModel::login,
                onResetState = viewModel::resetState
            )
        }
    }
}