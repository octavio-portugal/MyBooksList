package com.dio.mybookslist.Model.data.model

data class CategoriasResponse (
    var status : String,
    var results: List<CategoriasDetails>
        )

data class CategoriasDetails (
    var list_name: String,
    var list_name_encoded: String
        )