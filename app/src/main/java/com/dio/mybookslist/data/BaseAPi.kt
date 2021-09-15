package com.dio.mybookslist.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Base da conexao com a API
class BaseAPi {
    companion object{
            fun getRetrofitInstance(path: String): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
    }
}