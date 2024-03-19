package com.demo.docsolutions.core.data.network.api

import com.demo.docsolutions.core.common.model.Result
import com.demo.docsolutions.core.data.network.model.authentication.LoginRequestDTO
import com.demo.docsolutions.core.data.network.model.authentication.LoginResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * Authentication API
 */
interface AuthenticationAPI {

    /**
     * HTTP API for authenticate a user
     */
    @POST("$API/authentication")
    suspend fun login(@Body request: LoginRequestDTO): Result<LoginResponseDTO> //TODO Change the return type

    companion object {
        const val API = "authentication"
    }
}