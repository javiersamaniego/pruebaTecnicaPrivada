package com.demo.docsolutions.ui.stateholder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.demo.docsolutions.ui.feature.authentication.navigation.AuthenticationNavigation
import com.demo.docsolutions.ui.feature.user.navigation.UserCreationNavigation
import com.demo.docsolutions.ui.feature.user.navigation.UserNavigation
import com.demo.docsolutions.ui.navigation.AppDestination

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(navController) {
        AppState(navController)
    }
}

/**
 *
 */
@Stable
class AppState(
    val navController: NavHostController
) {

    fun navigateToDestination(destination: AppDestination) {

        when (destination) {
            AppDestination.AUTHENTICATION_LOGIN -> {
                navController.navigate(AuthenticationNavigation.navigateAuthenticationLogin())
            }

            AppDestination.USERS -> {
                navController.navigate(UserNavigation.navigateUsers())
            }

            AppDestination.CREATE_USER -> {
                navController.navigate(UserCreationNavigation.navigateUserCreation())
            }
        }
    }
}