package com.demo.docsolutions.ui.feature.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.docsolutions.core.common.model.onError
import com.demo.docsolutions.core.common.model.onException
import com.demo.docsolutions.core.common.model.onSuccess
import com.demo.docsolutions.core.domain.user.usecase.CreateUserUseCase
import com.demo.docsolutions.ui.feature.user.model.UserCreationState
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
class UserCreationViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    var username: String by mutableStateOf("")
        private set

    var password: String by mutableStateOf("")
        private set

    var name: String by mutableStateOf("")
        private set

    var fatherLastName: String by mutableStateOf("")
        private set

    var motherLastName: String by mutableStateOf("")
        private set

    var email: String by mutableStateOf("")
        private set

    var phone: String by mutableStateOf("")
        private set

    private val _state: MutableStateFlow<UserCreationState> =
        MutableStateFlow(UserCreationState.Idle)
    val state: StateFlow<UserCreationState> get() = _state


    fun onUpdateUserName(username: String) {
        this.username = username
    }

    fun onUpdatePassword(password: String) {
        this.password = password
    }

    fun onUpdateName(name: String) {
        this.name = name
    }

    fun onUpdateFatherLastName(fatherLastName: String) {
        this.fatherLastName = fatherLastName
    }

    fun onUpdateMotherLastName(motherLastName: String) {
        this.motherLastName = motherLastName
    }

    fun onUpdateEmail(email: String) {
        this.email = email
    }

    fun onUpdatePhone(phone: String) {
        this.phone = phone
    }

    fun resetState(){
        _state.update { UserCreationState.Idle }
    }

    fun createUser() {
        areFieldsValid {
            viewModelScope.launch {
                _state.update { UserCreationState.Loading }
                createUserUseCase(
                    username = username,
                    password = password,
                    name = name,
                    fatherLastName = fatherLastName,
                    motherLastName = motherLastName,
                    email = email,
                    phone = phone
                ).onSuccess {
                    _state.update { UserCreationState.OnCreated }
                }.onError { _, message ->
                    _state.update { UserCreationState.OnError(message) }
                }.onException { e ->
                    _state.update { UserCreationState.OnError(e.message.toString()) }
                }
            }
        }
    }

    fun areFieldsValid(execute: () -> Unit) {
        if (username.isNotEmpty() &&
            password.isNotEmpty() &&
            name.isNotEmpty() &&
            fatherLastName.isNotEmpty() &&
            motherLastName.isNotEmpty() &&
            email.isNotEmpty() &&
            phone.isNotEmpty()
        ) {
            execute()
        }
    }
}