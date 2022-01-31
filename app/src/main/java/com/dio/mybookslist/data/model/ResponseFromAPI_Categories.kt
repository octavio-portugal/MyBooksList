package com.dio.mybookslist.data.model

data class CategoriesResponse (
//    var status : String,
    var results: List<CategoriesDetails>
        )

data class CategoriesDetails (
    var list_name: String,
    var list_name_encoded: String
        )