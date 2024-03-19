package com.demo.docsolutions.core.domain.user.usecase

import com.demo.docsolutions.core.domain.common.BaseUseCase
import com.demo.docsolutions.core.domain.user.repository.UserRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * Use case to create a new user
 */
class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase() {

    /**
     * Executes the use case to create a new user
     */
    suspend operator fun invoke(
        username: String,
        password: String,
        name: String,
        fatherLastName: String,
        motherLastName: String,
        email: String,
        phone: String
    ) = withContext(defaultDispatcher) {
        userRepository.create(
            username,
            password,
            name,
            fatherLastName,
            motherLastName,
            email,
            phone
        )
    }
}