package com.demo.docsolutions.core.data.network.model

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type
import javax.inject.Inject
import com.demo.docsolutions.core.common.model.Result

/**
 * @author Javier on 2024/03/19
 * @version 1.0
 * @since 1.0
 */

/**
 *
 */
class NetworkResultCallAdapter @Inject constructor(
    private val resultType: Type
): CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType


    override fun adapt(call: Call<Type>): Call<Result<Type>> {
       return  NetworkResultCall(call)
    }
}
