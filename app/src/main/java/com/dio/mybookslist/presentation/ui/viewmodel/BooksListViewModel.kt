package com.dio.mybookslist.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.model.toBook
import com.dio.mybookslist.data.repository.BooksRepostitory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class BooksListViewModel(private val repository: BooksRepostitory) : ViewModel() {
    private val booksModel = BooksModel()
    private val _booksLivedata: MutableLiveData<BooksModel> = MutableLiveData()
    val booksLivedata: LiveData<BooksModel>
        get() =_booksLivedata

    fun makeApiCall() {
        viewModelScope.async(Dispatchers.IO) {
            booksModel.toBook(repository.getBooks())
            _booksLivedata.value = booksModel
            Log.d("BooksLiveData", booksLivedata.toString())
        }
    }

    fun getBooksListObserver(): MutableLiveData<BooksModel> {
        return _booksLivedata
        Log.e("View Model Resposta", booksLivedata.toString())
    }
}