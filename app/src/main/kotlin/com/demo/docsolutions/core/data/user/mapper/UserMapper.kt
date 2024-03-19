package com.demo.docsolutions.core.data.user.mapper

import com.demo.docsolutions.core.data.network.model.user.SearchUserResponseBodyDTO
import com.demo.docsolutions.core.data.network.model.user.SearchUserResponseDTO
import com.demo.docsolutions.core.domain.user.model.User

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
fun SearchUserResponseBodyDTO.toDomain() = User(
    id = id,
    username = username,
    name = name,
    fatherLastName = fatherLastName,
    creationDate = creationDate,
    email = email.orEmpty(),
    phone = phone.orEmpty()
)