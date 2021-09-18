package com.dio.mybookslist.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dio.mybookslist.data.ApiService
import com.dio.mybookslist.data.model.BooksModel

class BooksViewModel: ViewModel() {

    val booksLiveData: MutableLiveData<List<BooksModel>> = MutableLiveData()

    fun getBooks(){
//        ApiService.getResponse()

    }

}