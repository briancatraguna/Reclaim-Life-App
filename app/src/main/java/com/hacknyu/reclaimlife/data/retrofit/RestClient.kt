package com.hacknyu.reclaimlife.data.retrofit

import com.hacknyu.reclaimlife.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private const val QUOTES_BASE_URL: String = BuildConfig.QUOTES_BASE_URL

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val quotesRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(QUOTES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val quotesService: QuotesService by lazy {
        quotesRetrofit.create(QuotesService::class.java)
    }

    private const val RECLAIM_BASE_URL: String = BuildConfig.RECLAIM_BASE_URL

    private val reclaimBackendRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(RECLAIM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val backendService: BackendService by lazy {
        reclaimBackendRetrofit.create(BackendService::class.java)
    }
}