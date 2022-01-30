package com.dio.mybookslist.data.di

import com.dio.mybookslist.data.repository.BooksRepostitory
import com.dio.mybookslist.data.repository.RepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DataModule {

    fun load(){
        loadKoinModules(bookModule())
    }

    private fun bookModule() : Module {
        return module{
            single<BooksRepostitory> { RepositoryImpl() }
        }
    }
}