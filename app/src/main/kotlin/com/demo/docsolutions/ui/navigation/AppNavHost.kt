package com.demo.docsolutions.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.demo.docsolutions.ui.feature.authentication.navigation.AuthenticationNavigation
import com.demo.docsolutions.ui.feature.user.navigation.UserCreationNavigation
import com.demo.docsolutions.ui.feature.user.navigation.UserNavigation
import com.demo.docsolutions.ui.stateholder.AppState
import com.demo.docsolutions.ui.stateholder.rememberAppState

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
@Composable
fun AppNavHost(
    appState: AppState = rememberAppState(),
    startDestination: String = AuthenticationNavigation.AUTHENTICATION_LOGIN_ROUTE
) {

    NavHost(navController = appState.navController, startDestination = startDestination) {

        AuthenticationNavigation.authenticationLogin(
            navGraphBuilder = this,
            onNavigateToUsers = {
                appState.navigateToDestination(AppDestination.USERS)
            }
        )

        UserNavigation.users(navGraphBuilder = this, onNavigateToCreateUser = {
            appState.navigateToDestination(AppDestination.CREATE_USER)
        })

        UserCreationNavigation.userCreation(navGraphBuilder = this, onBackPressed = {
            appState.navController.popBackStack()
        })
    }
}