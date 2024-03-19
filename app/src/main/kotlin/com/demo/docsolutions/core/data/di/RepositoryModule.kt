package com.demo.docsolutions.core.data.di

import com.demo.docsolutions.core.data.authentication.repository.AuthenticationRepositoryImpl
import com.demo.docsolutions.core.data.user.repository.UserRepositoryImpl
import com.demo.docsolutions.core.domain.authentication.repository.AuthenticationRepository
import com.demo.docsolutions.core.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthenticationRepositoryImpl(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun bindUserRepositoryImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository
}