package com.dio.mybookslist.data.repository

import android.util.Log
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.model.DetailsResponse
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.service.ApiServiceBooks
import com.dio.mybookslist.data.service.BaseAPi

/*

Classe ainda não implementada
 */

interface BooksRepostitory {

    suspend fun getBooks(): ResponseModel
}

class RepositoryImpl() : BooksRepostitory {
    private val apiKey: String
        get() = BuildConfig.API_KEY

    private val list = "hardcover-fiction"

    override suspend fun getBooks(): ResponseModel {
        return try {
            val books_url = BaseAPi.SERVICE_BOOK
            val retrofitBooksInstance = BaseAPi.getRetrofitInstance(books_url)
                .create(ApiServiceBooks::class.java)
            val response = retrofitBooksInstance.getResponseBooksList(list, apiKey)
            Log.d("RESPOSTA", response.toString())
            return response
        }finally { }
    }
}


//            val list = ArrayList(response.results.books)
//                for( i in list){
//                    val book: DetailsResponse = DetailsResponse(
//                        i.description,
//                        i.publisher,
//                        i.title,
//                        i.author,
//                        i.book_image
//                    )
//                }
//