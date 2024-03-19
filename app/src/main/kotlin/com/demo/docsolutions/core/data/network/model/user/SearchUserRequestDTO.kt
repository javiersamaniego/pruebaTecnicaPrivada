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
data class SearchUserRequestDTO(
    @SerializedName("Body")
    val body: SearchUserBodyRequestDTO
)

data class SearchUserBodyRequestDTO(
    @SerializedName("SearchText")
    val searchText: String
)