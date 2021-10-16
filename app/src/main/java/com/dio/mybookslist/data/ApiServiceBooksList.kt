package com.dio.mybookslist.data

import com.dio.mybookslist.data.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceBooksList {
    @GET("current/{lists}.json?")
    fun getResponseBooksList(
        @Path("lists") list: String ,
        @Query("api-key") apikey: String,
    ): Call<ResponseModel>

}

interface ApiServiceCategoriasList {
    @GET ("names.json?")
    fun getResponseCategoriasList(
        @Query("api-key") apikey: String
    )
}

