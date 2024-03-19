package com.demo.docsolutions.core.data.network.model.user

import com.google.gson.annotations.SerializedName

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
data class CreateUserResponseDTO(
    @SerializedName("IsOK")
    val isOK: Boolean,

    @SerializedName("Messages")
    val messages: String?
)