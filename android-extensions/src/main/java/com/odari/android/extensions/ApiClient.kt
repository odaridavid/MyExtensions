package com.odari.android.extensions

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiClient {

    private fun setupInterceptor(loggingLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = loggingLevel
        }

    private fun setupOkhttpClient(
        connectTimeOutMilliseconds: Long,
        readTimeOutMilliseconds: Long,
        loggingLevel: HttpLoggingInterceptor.Level
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(setupInterceptor(loggingLevel))
            .connectTimeout(connectTimeOutMilliseconds, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeOutMilliseconds, TimeUnit.MILLISECONDS)
            .build()
    }

    /**
     * @param baseUrl The host url to the endpoints you want to call
     * @param apiService an interface defining the endpoints with request type
     * @param factory creates converter instance for serialisation of response
     * @param connectTimeOutMilliseconds maximum timeout value for client to make the initial connection
     * @param readTimeOutMilliseconds maximum timeout on waiting to read data
     * @param loggingLevel requests and response log level
     *
     * @return an implementation of the service interface
     */
    fun <T> getInstance(
        baseUrl: String,
        apiService: Class<T>,
        factory: Converter.Factory,
        connectTimeOutMilliseconds: Long = 300,
        readTimeOutMilliseconds: Long = 300,
        loggingLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                setupOkhttpClient(
                    connectTimeOutMilliseconds,
                    readTimeOutMilliseconds,
                    loggingLevel
                )
            )
            .addConverterFactory(factory)
            .build()
            .create(apiService)
    }
}