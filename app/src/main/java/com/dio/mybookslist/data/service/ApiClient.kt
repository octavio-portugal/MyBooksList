package com.dio.mybookslist.data.service
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.model.ResponseModel
import retrofit2.Response


/*
Classe não implentada ainda.

 */

//class ApiClient(private val booksService: ApiServiceBooks) {
//
//    private val apiKey: String
//        get() = BuildConfig.API_KEY
//
//    private val list = "hardcover-fiction"
//
//    suspend fun getBooksService(): SimpleResponse<ResponseModel>{
//        return safeApiCall{booksService.getResponseBooksList(list,apiKey)}
//    }
//
//    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T>{
//        return try {
//            SimpleResponse.success(apiCall.invoke())
//        } catch (e: Exception) {
//            SimpleResponse.failure(e)
//        }
//    }
//}