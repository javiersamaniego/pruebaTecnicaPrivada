package com.demo.docsolutions.ui.feature.user.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.docsolutions.ui.feature.user.UserCreationScreenRoute
import com.demo.docsolutions.ui.feature.user.UsersRoute

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
object UserCreationNavigation {

    private const val USER_CREATION_ROUTE = "user_creation"

    fun navigateUserCreation() = USER_CREATION_ROUTE

    fun userCreation(
        navGraphBuilder: NavGraphBuilder,
        onBackPressed: () -> Unit
    ) {
        navGraphBuilder.composable(
            route = USER_CREATION_ROUTE
        ) {

          UserCreationScreenRoute(onFinish = onBackPressed)
        }
    }
}