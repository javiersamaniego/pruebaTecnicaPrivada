package com.demo.docsolutions.core.domain.authentication.usecase

import com.demo.docsolutions.core.domain.authentication.repository.AuthenticationRepository
import com.demo.docsolutions.core.domain.common.BaseUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * Use case to login a user
 */
class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseUseCase() {

    /**
     * Executes the use case to login a user
     */
    suspend operator fun invoke(username: String, password: String) =
        withContext(defaultDispatcher) {
            authenticationRepository.login(username, password)
        }
}