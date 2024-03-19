package com.demo.docsolutions.core.data.user.repository

import com.demo.docsolutions.core.common.model.Result
import com.demo.docsolutions.core.data.network.api.UserAPI
import com.demo.docsolutions.core.data.network.model.user.CreateUserRequestBodyDTO
import com.demo.docsolutions.core.data.network.model.user.CreateUserRequestDTO
import com.demo.docsolutions.core.data.network.model.user.SearchUserBodyRequestDTO
import com.demo.docsolutions.core.data.network.model.user.SearchUserRequestDTO
import com.demo.docsolutions.core.data.user.mapper.toDomain
import com.demo.docsolutions.core.domain.user.model.User
import com.demo.docsolutions.core.domain.user.repository.UserRepository
import javax.inject.Inject

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
class UserRepositoryImpl @Inject constructor(
    private val userAPI: UserAPI
) : UserRepository {

    override suspend fun search(searchText: String): Result<List<User>> {
        val response = userAPI.search(SearchUserRequestDTO(SearchUserBodyRequestDTO(searchText)))
        return when (response) {
            is Result.Success -> {
                if (response.data.isOK) {
                    Result.Success(response.data.body.map { it.toDomain() })
                } else {
                    Result.Error(message = response.data.messages)
                }
            }

            is Result.Error -> Result.Error(response.code, response.message)
            is Result.Exception -> Result.Exception(response.exception)
        }
    }

    override suspend fun create(
        username: String,
        password: String,
        name: String,
        fatherLastName: String,
        motherLastName: String,
        email: String,
        phone: String
    ): Result<Boolean> {

        val response = userAPI.create(
            CreateUserRequestDTO(
                CreateUserRequestBodyDTO(
                    username = username,
                    password = password,
                    name = name,
                    fatherLastName = fatherLastName,
                    motherLastName = motherLastName,
                    email = email,
                    phone = phone
                )
            )
        )
        return when (response) {
            is Result.Success -> {
                if (response.data.isOK) {
                    Result.Success(true)
                } else {
                    Result.Error(message = response.data.messages.orEmpty())
                }
            }

            is Result.Error -> Result.Error(response.code, response.message)
            is Result.Exception -> Result.Exception(response.exception)
        }
    }
}