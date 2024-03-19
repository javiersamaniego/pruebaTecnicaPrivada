package com.demo.docsolutions.ui.feature.user.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.docsolutions.ui.feature.user.UserViewModel
import com.demo.docsolutions.ui.feature.user.UsersRoute
import com.demo.docsolutions.ui.feature.user.model.UserState

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
object UserNavigation {

    const val USERS_ROUTE = "users"

    fun navigateUsers() = USERS_ROUTE

    fun users(
        navGraphBuilder: NavGraphBuilder,
        onNavigateToCreateUser: () -> Unit
    ) {
        navGraphBuilder.composable(
            route = USERS_ROUTE
        ) {

            val viewModel: UserViewModel = hiltViewModel()
            val state: UserState by viewModel.state.collectAsStateWithLifecycle()

            UsersRoute(
                state = state,
                searchText = viewModel.searchText,
                onUpdateSearchText = viewModel::updateSearchText,
                onSearch = viewModel::search,
                onCreateUser = onNavigateToCreateUser
            )
        }
    }
}