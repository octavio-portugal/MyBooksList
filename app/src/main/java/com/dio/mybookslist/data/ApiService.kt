package com.dio.mybookslist.data

import com.dio.mybookslist.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("hardcover-fiction.json?")
    fun getResponse(
        @Query("api-key") apikey: String,
    ): Call<ResponseModel>


}