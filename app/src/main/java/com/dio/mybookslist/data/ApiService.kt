package com.dio.mybookslist.data

import com.dio.mybookslist.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("hardcover-fiction.json?api-key=FVWR9iNfBhiZIgnvCOa4dLjX6ucfK9Wb")
    fun getResponse(
//        @Query("yourkey") apikey: String = "FVWR9iNfBhiZIgnvCOa4dLjX6ucfK9Wb",
//        @Query("list") list: String = "hardcover-fiction"
    ): Call<ResponseModel>


}