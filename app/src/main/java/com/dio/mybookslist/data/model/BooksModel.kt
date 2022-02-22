package com.dio.mybookslist.data.model

import android.util.Log
import com.dio.mybookslist.R

data class BooksModel(
    var title_model: String? = null,
    var author_model: String? = null,
    var descrition_model: String? = null,
    var image_model: String? = null,
    var publisher_model: String? = null,
//    var rank: Int
)

fun BooksModel.toBook(mainCallResponse: ResponseModel?): BooksModel {

    val book = BooksModel("", "", "", "", "")

    if (mainCallResponse != null) {
        val mainResponse = mainCallResponse.results.books
        for (bookResponse in mainResponse) {

            book.title_model = bookResponse.title
            book.author_model = bookResponse.author
            book.descrition_model = bookResponse.description
            book.publisher_model = bookResponse.publisher
            book.image_model = bookResponse.book_image
        }

    } else {
        book.title_model = R.string.error_message.toString()
        book.author_model = R.string.error_message.toString()
        book.descrition_model = R.string.error_message.toString()
        book.publisher_model = R.string.error_message.toString()
        book.image_model = R.string.error_message.toString()
    }

    return book
}


