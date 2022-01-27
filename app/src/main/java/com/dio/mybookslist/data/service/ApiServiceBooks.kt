package com.dio.mybookslist.data.service

import com.dio.mybookslist.data.model.CategoriasResponse
import com.dio.mybookslist.data.model.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceBooks {
//    @GET("current/{lists}.json?")
//    fun getResponseBooksList(
//        @Path("lists") list: String ,
//        @Query("api-key") apikey: String,
//    ): Call<ResponseModel>

    @GET("current/{lists}.json?")
    fun getResponseBooksList(
        @Path("lists") list: String ,
        @Query("api-key") apikey: String,
    ): Deferred<ResponseModel>

}

interface ApiServiceCategorias {
    @GET ("names.json?")
    fun getResponseCategoriasList(
        @Query("api-key") apikey: String
    ): Call<CategoriasResponse>
}

