package com.dio.mybookslist.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("results")
    var results: ResultsResponse
)

data class ResultsResponse(
    var list_name: String,
    var books: List<DetailsResponse>,
)

data class DetailsResponse(
    var description: String,
    var publisher: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("author")
    var author: String,
    var book_image: String
)