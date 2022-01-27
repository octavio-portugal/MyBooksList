package com.dio.mybookslist.data.repository

import com.dio.mybookslist.data.model.BooksModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface BooksRepostitory {

    suspend fun getBooks(list: String, apikey: String) : List<BooksModel>
}