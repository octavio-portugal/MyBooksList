package com.dio.mybookslist.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.repository.BooksRepostitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class BooksListViewModel(private val repository: BooksRepostitory) : ViewModel() {
    var booksLivedata: MutableLiveData<ResponseModel> = MutableLiveData()

    fun getBooksListObserver(): MutableLiveData<ResponseModel> {
        return booksLivedata
    }

    fun makeApiCall() {
        viewModelScope.async(Dispatchers.IO) {
            booksLivedata.value = repository.getBooks()
        }
    }
}