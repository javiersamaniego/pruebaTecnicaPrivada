package com.demo.docsolutions.core.data.authentication.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.demo.docsolutions.core.common.model.Result
import com.demo.docsolutions.core.common.model.onSuccess
import com.demo.docsolutions.core.data.constant.DATA_STORE_TOKEN_PREFERENCE
import com.demo.docsolutions.core.data.network.api.AuthenticationAPI
import com.demo.docsolutions.core.data.network.model.authentication.LoginRequestContentDTO
import com.demo.docsolutions.core.data.network.model.authentication.LoginRequestDTO
import com.demo.docsolutions.core.domain.authentication.repository.AuthenticationRepository
import javax.inject.Inject

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationAPI: AuthenticationAPI,
    private val dataStore: DataStore<Preferences>
) : AuthenticationRepository {

    override suspend fun login(username: String, password: String): Result<Boolean> {

        val response = authenticationAPI.login(
            LoginRequestDTO(
                LoginRequestContentDTO(
                    username,
                    password
                )
            )
        ).onSuccess {
            if (it.isOk)
                saveToken(it.body.token)
        }

        return when (response) {
            is Result.Error -> {
                Result.Error(response.code, response.message)
            }

            is Result.Exception -> {
                Result.Exception(response.exception)
            }

            is Result.Success -> {
                if (response.data.isOk) {
                    Result.Success(true)
                } else {
                    Result.Error(message = response.data.messages)
                }
            }
        }
    }

    private suspend fun saveToken(token: String) {
        dataStore.edit {
            it[DATA_STORE_TOKEN_PREFERENCE] = token
        }
    }
}