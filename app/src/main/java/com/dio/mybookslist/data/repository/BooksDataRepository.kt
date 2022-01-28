package com.dio.mybookslist.data.repository

import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.toModel
import com.dio.mybookslist.data.service.ApiServiceBooks
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class BooksDataRepository (
    private val api: ApiServiceBooks
    ) : BooksRepostitory {

    override suspend fun getBooks(list: String, apikey: String) = listOf(
        withContext(IO) {
        api.getResponseBooksList(list, apikey).await().toModel()
    })
}

