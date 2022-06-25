package com.leoleo2.myokashi

import retrofit2.http.GET
import retrofit2.http.Query

interface OkashiService {
    @GET("toriko/api/")
    suspend fun searchOkashi(
        @Query("apikey") apikey: String = "guest",
        @Query("format") format: String = "json",
        @Query("keyword" ,encoded = true) keyword: String,
        @Query("max") max: String = "10",
        @Query("order") order: String = "r",
    ): APIResponse
}