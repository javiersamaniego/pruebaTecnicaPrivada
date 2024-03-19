package com.demo.docsolutions.core.domain.authentication.repository

import com.demo.docsolutions.core.common.model.Result

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
interface AuthenticationRepository {

    /**
     * Authenticate a user Voy a omitir los comentarios para completar en tiempo!
     */
    suspend fun login(username: String, password: String): Result<Any>
}