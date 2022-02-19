package com.dio.mybookslist.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.repository.BooksRepostitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class BooksListViewModel(private val repository: BooksRepostitory) : ViewModel() {
    private val _booksLivedata: MutableLiveData<ResponseModel> = MutableLiveData()
    val booksLivedata: LiveData<ResponseModel>
        get() =_booksLivedata

    fun makeApiCall() {
        viewModelScope.async(Dispatchers.IO) {
            _booksLivedata.value = repository.getBooks()
            Log.d("BooksLiveData", booksLivedata.toString())
        }
    }

    fun getBooksListObserver(): MutableLiveData<ResponseModel> {
        return _booksLivedata
        Log.e("View Model Resposta", booksLivedata.toString())
    }
}