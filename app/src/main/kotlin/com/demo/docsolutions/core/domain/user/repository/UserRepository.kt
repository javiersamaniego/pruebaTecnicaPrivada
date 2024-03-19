package com.demo.docsolutions.core.domain.user.repository

import com.demo.docsolutions.core.common.model.Result
import com.demo.docsolutions.core.domain.user.model.User

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
interface UserRepository {

    suspend fun search(searchText: String): Result<List<User>>

    suspend fun create(
        username: String,
        password: String,
        name: String,
        fatherLastName: String,
        motherLastName: String,
        email: String,
        phone: String
    ): Result<Boolean>
}