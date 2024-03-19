package com.demo.docsolutions.ui.feature.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.docsolutions.core.common.model.onError
import com.demo.docsolutions.core.common.model.onException
import com.demo.docsolutions.core.common.model.onSuccess
import com.demo.docsolutions.core.domain.authentication.usecase.LoginUseCase
import com.demo.docsolutions.ui.feature.authentication.model.AuthenticationState
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
class AuthenticationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var username by mutableStateOf("usr_prueba1")
        private set

    var password by mutableStateOf("Nuevo1234")
        private set


    private val _state: MutableStateFlow<AuthenticationState> =
        MutableStateFlow(AuthenticationState.Idle)
    val state: StateFlow<AuthenticationState> get() = _state


    fun updateUserName(newUserName: String) {
        username = newUserName
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun resetState() {
        _state.update { AuthenticationState.Idle }
    }

    fun login() {
        onFieldsValid {
            viewModelScope.launch {
                _state.update { AuthenticationState.Loading }

                loginUseCase(username, password).onSuccess {
                    _state.update { AuthenticationState.OnUserLogIn }
                }.onError { _, message ->
                    _state.update { AuthenticationState.OnError(message) }
                }.onException { e ->
                    _state.update { AuthenticationState.OnError(e.message.toString()) }
                }
            }
        }
    }

    fun onFieldsValid(callback: () -> Unit) {
        if (username.isNotEmpty() && password.isNotEmpty())
            callback()
    }
}