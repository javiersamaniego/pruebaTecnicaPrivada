package com.demo.docsolutions.core.common.model

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 * Represents multiple resource results
 */
sealed class Result<out T> {


    /**
     * Represent a success result state
     * @property data Data of the result
     */
    data class Success<T>(val data: T) : Result<T>()

    /**
     * Represent an error result state
     * @property code Error code
     * @property message Error message
     */
    data class Error(val code: Int = -1, val message: String = "") : Result<Nothing>()


    /**
     * Represent an exception result state
     * @property exception The error exception
     */
    data class Exception(val exception: kotlin.Exception) : Result<Nothing>()
}

/**
 * Execute a block of code when the result state is "success"
 * @param executable Block of code to execute when the state of the result is "success"
 */
inline fun <T : Any> Result<T>.onSuccess(
    executable: (data: T) -> Unit
): Result<T> = apply {
    if (this is Result.Success) {
        executable(data)
    }
}

/**
 * Execute a block of code when the result state is "error"
 * @param executable Block of code to execute when the state of the result is "error"
 *                   passing the code and message of the error
 */
inline fun <T : Any> Result<T>.onError(
    executable: (code: Int, message: String) -> Unit
): Result<T> = apply {
    if (this is Result.Error) {
        executable(code, message)
    }
}

/**
 * Execute a block of code when the result state is "error"
 * @param executable Block of code to execute when the state of the result is "error"
 *                   passing the exception
 */
inline fun <T : Any> Result<T>.onException(
    executable: (exception: Exception) -> Unit
): Result<T> = apply {
    if (this is Result.Exception) {
        executable(exception)
    }
}

fun <T: Any, R> Result<T>.map(transform: (value: T) -> R): Result<R> {
    return when(this) {
        is Result.Success -> {
            val transData = transform(this.data)
            Result.Success(transData)
        }
        is Result.Error -> this
        is Result.Exception -> this
    }
}