package com.dio.mybookslist.presentation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.model.CategoriesResponse
import com.dio.mybookslist.data.service.ApiServiceBooks
import com.dio.mybookslist.data.service.ApiServiceCategorias
import com.dio.mybookslist.data.service.BaseAPi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CategoriesViewModel : ViewModel() {

    private val apiKey: String
        get() = BuildConfig.API_KEY

    var categoriesLivedata: MutableLiveData<CategoriesResponse> = MutableLiveData()

    fun getCategoriesListObserver(): MutableLiveData<CategoriesResponse> {
        return categoriesLivedata
    }

    fun makeApiCall() {
        viewModelScope.async(Dispatchers.IO) {
            val categories_url = BaseAPi.SERVICE_CATEGORIES
            val retrofitCategoriesInstance = BaseAPi.getRetrofitInstance(categories_url)
                .create(ApiServiceCategorias::class.java)
            val response = retrofitCategoriesInstance.getResponseCategoriasList(apiKey)
            categoriesLivedata.postValue(response)
        }
    }
}
