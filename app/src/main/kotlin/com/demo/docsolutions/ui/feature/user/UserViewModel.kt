package com.demo.docsolutions.ui.feature.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.docsolutions.core.common.model.onError
import com.demo.docsolutions.core.common.model.onException
import com.demo.docsolutions.core.common.model.onSuccess
import com.demo.docsolutions.core.domain.user.usecase.GetUsersUseCase
import com.demo.docsolutions.ui.feature.user.mapper.toUI
import com.demo.docsolutions.ui.feature.user.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var searchText: String by mutableStateOf("")
        private set

    private val _state: MutableStateFlow<UserState> = MutableStateFlow(UserState.Idle)
    val state: StateFlow<UserState> get() = _state

    fun updateSearchText(newSearchText: String) {
        searchText = newSearchText
    }

    fun search() {
        areFieldsValid {
            viewModelScope.launch {
                _state.update { UserState.Loading }

                getUsersUseCase(searchText).onSuccess {
                    val users = it.map { user -> user.toUI() }
                    _state.update { UserState.OnSearchUser(users) }
                }.onError { _, message ->
                    _state.update { UserState.OnError(message) }
                }.onException { e ->
                    _state.update { UserState.OnError(e.message.toString()) }
                }

            }
        }
    }

    fun areFieldsValid(execute: () -> Unit) {
        if (searchText.isNotEmpty())
            execute()
    }
}