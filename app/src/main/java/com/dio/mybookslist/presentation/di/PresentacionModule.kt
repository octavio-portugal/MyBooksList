package com.dio.mybookslist.presentation.di

import com.dio.mybookslist.presentation.ui.viewmodel.BooksViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentacionModule {

    fun load(){
        loadKoinModules(viewModelModule())
    }


    private fun viewModelModule(): Module {
        return  module {
            factory { BooksViewModel() }
        }
    }


}