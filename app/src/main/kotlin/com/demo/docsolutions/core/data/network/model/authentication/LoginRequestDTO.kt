package com.demo.docsolutions.core.data.network.model.authentication

import com.google.gson.annotations.SerializedName

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * Login request DTO
 * @property username The user account username
 * @property password The user account password
 */
data class LoginRequestDTO(

    @SerializedName("body")
    val body: LoginRequestContentDTO
)

data class LoginRequestContentDTO(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String
)