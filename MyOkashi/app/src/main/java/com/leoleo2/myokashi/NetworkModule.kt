package com.leoleo2.myokashi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
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
    val okashiService: OkashiService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://sysbird.jp/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(OkashiService::class.java)
}