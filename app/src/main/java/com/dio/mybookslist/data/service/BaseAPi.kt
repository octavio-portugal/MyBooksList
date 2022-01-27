package com.dio.mybookslist.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Base da conexao com a API
//class BaseAPi {
     object BaseAPi{
              fun getRetrofitInstance(path: String): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
    val SERVICE_BOOK: ApiServiceBooks = getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/").create(
        ApiServiceBooks::class.java)
    val SERVICE_CATEGIES: ApiServiceCategorias = getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/").create(
        ApiServiceCategorias::class.java)
    }

//}