package com.dio.mybookslist.data.repository

import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.service.ApiServiceBooksList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

class BooksRepositoryIml(private val service: ApiServiceBooksList) {

    private val apiKey: String
        get() = BuildConfig.API_KEY

    private  lateinit var url_categories: String

     suspend fun listBooksFlow(): Flow<Call<ResponseModel>> = flow {

        url_categories = "hardcover-fiction"

        val bookList = service.getResponseBooksList(url_categories, apiKey)
        emit(bookList)
    }
}