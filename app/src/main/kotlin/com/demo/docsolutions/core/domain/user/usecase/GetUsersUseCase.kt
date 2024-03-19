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
 * Use case to get a list of users
 */
class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase() {

    /**
     * Executes the use case to get a list of users
     */
    suspend operator fun invoke(searchText: String) = withContext(defaultDispatcher) {

        userRepository.search(searchText)
    }
}