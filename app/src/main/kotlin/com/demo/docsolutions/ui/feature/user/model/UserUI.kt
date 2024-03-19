package com.demo.docsolutions.ui.feature.user.model

import com.google.gson.annotations.SerializedName

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
data class UserUI(
    @SerializedName("Id")
    val id: Long,

    @SerializedName("Username")
    val username: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("FatherLastName")
    val fatherLastName: String,

    @SerializedName("CreationDate")
    val creationDate: String,

    @SerializedName("Email")
    val email:String,

    @SerializedName("PhoneNumber")
    val phone: String,
)