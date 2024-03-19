package com.demo.docsolutions.core.data.network.model.authentication

import com.google.gson.annotations.SerializedName

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
data class LoginResponseDTO(

    @SerializedName("Body")
    val body: LoginResponseBodyDTO,

    @SerializedName("Messages")
    val messages: String,

    @SerializedName("IsOK")
    val isOk: Boolean
)

data class LoginResponseBodyDTO(
    @SerializedName("Token")
    val token: String
)