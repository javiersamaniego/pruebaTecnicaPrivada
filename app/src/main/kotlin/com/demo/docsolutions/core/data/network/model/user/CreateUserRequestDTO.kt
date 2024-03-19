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
data class CreateUserRequestDTO(
    @SerializedName("Body")
    val body: CreateUserRequestBodyDTO
)

data class CreateUserRequestBodyDTO(

    @SerializedName("UserName")
    val username: String,

    @SerializedName("Password")
    val password: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("FatherLastName")
    val fatherLastName: String,

    @SerializedName("MotherLastName")
    val motherLastName: String,

    @SerializedName("Email")
    val email: String,

    @SerializedName("PhoneNumber")
    val phone: String,

    @SerializedName("Tenant")
    val tenant: String? = null,

    @SerializedName("Metadata")
    val metaData: String? = null,

    @SerializedName("Roles")
    val roles: List<UserRole> = listOf(UserRole(2, "Usuario Tradicional"))
)

data class UserRole(val id: Int, val name: String)