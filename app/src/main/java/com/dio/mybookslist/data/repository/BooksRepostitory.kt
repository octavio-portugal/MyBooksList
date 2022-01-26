package com.dio.mybookslist.data.repository

import com.dio.mybookslist.data.model.BooksModel
import kotlinx.coroutines.flow.Flow

interface BooksRepostitory {

    suspend fun listBooks() : Flow<List<BooksModel>>
}