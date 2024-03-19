package com.demo.docsolutions.core.data.network.model

import com.demo.docsolutions.core.common.model.Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call

import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
class NetworkResultCall<T : Any> @Inject constructor(
    private val proxy: Call<T>
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = callApi { response }
                callback.onResponse(this@NetworkResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {

                val error = Result.Exception(Exception(t))
                callback.onResponse(this@NetworkResultCall, Response.success(error))
            }
        })
    }

    override fun clone(): Call<Result<T>> = NetworkResultCall(proxy.clone())

    override fun execute(): Response<Result<T>> = throw NotImplementedError()

    override fun isExecuted() = proxy.isExecuted

    override fun cancel() {
        proxy.cancel()
    }

    override fun isCanceled() = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()

    fun <T : Any> callApi(execute: () -> Response<T>): Result<T> {
        return try {

            val response = execute()
            val body = response.body()!!

            if (response.isSuccessful) {
                Result.Success(body)
            } else {
                // TODO
                Result.Error(response.code(), response.message())
            }

        } catch (e: HttpException) {
            Result.Exception(e)
        } catch (e: Throwable) {
            Result.Exception(Exception(e))
        }
    }
}