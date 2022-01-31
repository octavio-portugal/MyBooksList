package com.dio.mybookslist.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dio.mybookslist.data.model.DetailsResponse
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.model.ResultsResponse
import com.dio.mybookslist.data.repository.BooksRepostitory
import io.mockk.*
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

lateinit var response: ResponseModel

class BooksListViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val repository = mockk<BooksRepostitory>()
    private val booksLivedataObserver = mockk<Observer<ResponseModel>>(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when viewmodel fetches data then it should call repository`(){
        val viewModel = instanciateViewModel()
        val book = listOf<DetailsResponse>()
        val result = ResultsResponse("hard", book)

        response = ResponseModel(result)


        coEvery { repository.getBooks() } returns  response

        viewModel.makeApiCall()

        coVerify { repository. getBooks()}
        verify { booksLivedataObserver.onChanged(response) }
    }

    private fun instanciateViewModel(): BooksListViewModel{
        val viewModel =  BooksListViewModel(repository)
        viewModel.booksLivedata.observeForever(booksLivedataObserver)
        return viewModel

    }
}

