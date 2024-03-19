package com.demo.docsolutions.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.demo.docsolutions.core.data.constant.DATA_STORE_TOKEN_PREFERENCE
import com.demo.docsolutions.core.data.network.api.AuthenticationAPI
import com.demo.docsolutions.core.data.network.api.UserAPI
import com.demo.docsolutions.core.data.network.model.NetworkResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

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
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(
        callAdapter: NetworkResultCallAdapterFactory,
        dataStore: DataStore<Preferences>
    ): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)

        httpClient.addInterceptor(Interceptor { chain ->
            val originalRequest: Request = chain.request()

            var newRequest: Request = originalRequest.newBuilder().build()
            runBlocking {

                val token = dataStore.data.map {
                    it[DATA_STORE_TOKEN_PREFERENCE]
                }.catch {
                    emit("")
                }.firstOrNull() ?: ""

                if (token.isNotEmpty()) {
                    newRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer $token")
                        .method(originalRequest.method, originalRequest.body)
                        .build()
                }
            }

            val response = chain.proceed(newRequest)
            response
        })

        return Retrofit.Builder().baseUrl(API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(callAdapter)
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkCallAdapterFactory() = NetworkResultCallAdapterFactory()

    @Singleton
    @Provides
    fun provideAuthenticationAPI(retrofit: Retrofit): AuthenticationAPI =
        retrofit.create(AuthenticationAPI::class.java)

    @Singleton
    @Provides
    fun provideUserAPI(retrofit: Retrofit): UserAPI = retrofit.create(UserAPI::class.java)

    private const val API_BASE_PATH = "https://techhub.docsolutions.com/OnBoardingPre/WebApi/api/"
    private const val CONNECTION_TIMEOUT_SECONDS = 30L
    private const val CONNECTION_READ_TIMEOUT_SECONDS = 30L
}