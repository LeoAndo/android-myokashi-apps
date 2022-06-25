package com.leoleo2.myokashi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val okashiService: OkashiService =
        Retrofit.Builder()
            .callFactory { request -> okHttpClient.newCall(request) }
            .baseUrl("https://sysbird.jp/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(OkashiService::class.java)

    private val okHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.build()
    }
    private val httpLoggingInterceptor by lazy {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor
    }
}