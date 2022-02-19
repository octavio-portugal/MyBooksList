package com.dio.mybookslist.presentation.di

import com.dio.mybookslist.data.model.ResultsResponse
import com.dio.mybookslist.data.repository.BooksRepostitory
import com.dio.mybookslist.presentation.ui.viewmodel.BooksListViewModel
import com.dio.mybookslist.presentation.ui.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentacionModule {

    fun load(){
        loadKoinModules(viewModelModule())
    }


    private fun viewModelModule(): Module {
        return  module {
            viewModel { BooksListViewModel(get())}
            viewModel { CategoriesViewModel()}
        }
    }


}