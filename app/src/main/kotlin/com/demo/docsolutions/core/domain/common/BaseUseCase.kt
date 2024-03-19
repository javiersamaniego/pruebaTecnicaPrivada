package com.demo.docsolutions.core.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * Base use case class for all use cases
 * @property defaultDispatcher The default coroutine dispatcher to run the use cases
 */
abstract class BaseUseCase(protected val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default)