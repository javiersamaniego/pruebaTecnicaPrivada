package com.demo.docsolutions.core.data.network.api

import com.demo.docsolutions.core.common.model.Result
import com.demo.docsolutions.core.data.network.model.user.CreateUserRequestDTO
import com.demo.docsolutions.core.data.network.model.user.CreateUserResponseDTO
import com.demo.docsolutions.core.data.network.model.user.SearchUserRequestDTO
import com.demo.docsolutions.core.data.network.model.user.SearchUserResponseDTO
import com.demo.docsolutions.core.domain.user.model.User
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * User API
 */
interface UserAPI {

    /**
     * HTTP API for search a user
     */
    @POST("$API/GetUsers")
    suspend fun search(@Body request: SearchUserRequestDTO): Result<SearchUserResponseDTO> //TODO Change the return type

    @POST("$API/RegisterUserRole")
    suspend fun create(@Body request: CreateUserRequestDTO): Result<CreateUserResponseDTO>

    companion object {
        const val API = "user"
    }
}