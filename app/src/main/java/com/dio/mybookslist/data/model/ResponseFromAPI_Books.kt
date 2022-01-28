package com.dio.mybookslist.data.model

import com.google.gson.annotations.SerializedName
import org.koin.core.definition.indexKey

data class ResponseModel(
    var status: String,
    var copyright: String,
    var num_results: String,
    var last_modified: String,
    @SerializedName("results")
    var results: ResultsResponse
)

data class ResultsResponse(
    var list_name: String,
    var display_name: String,
    var bestsellers_date: String,
    var published_date: String,
    var rank_last_week: Int,
    var weeks_on_list: Int,
    var asterisk: Int,
    var dagger: Int,
    var amazon_product_url: String,
    var isbns: List<IsbnsResponse>,
    var books :List<DetailsResponse>,
    var reviews: List<ResviewsResponse>
)

data class DetailsResponse(
    var description: String,
    var contributor: String,
    var contributor_note: String,
    var price: String,
    var age_group: String,
    var rank: Int,
    var publisher: String,
    var primary_isbn13: String,
    var primary_isbn10: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("author")
    var author: String,
    var book_image:String
    )

data class ResviewsResponse(
    var book_review_link: String,
    var first_chapter_link: String,
    var sunday_review_link: String,
    var article_chapter_link: String
)


data class IsbnsResponse(
    var isbn10: String,
    var isbn13: String
)

fun ResponseModel.toModel(){
    val arrayBooksResults = ArrayList(this.results.books)

    for (i in arrayBooksResults){
        BooksModel(
            i.title,
            i.author,
            i.description,
            i.book_image,
            i.publisher
        )
    }
}
//fun ResponseModel.toModel() = BooksModel(this.status)