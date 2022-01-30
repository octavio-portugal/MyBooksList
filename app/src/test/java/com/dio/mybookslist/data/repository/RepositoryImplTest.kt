package com.dio.mybookslist.data.repository

import com.dio.mybookslist.data.model.DetailsResponse
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.model.ResultsResponse
import com.dio.mybookslist.data.service.ApiServiceBooks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test


lateinit var response: ResponseModel

class RepositoryImplTest : TestCase()


    private val testDispatcher = StandardTestDispatcher()
    private val apiService: ApiServiceBooks = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `when getBooks is called then it should call service ApiServiceBooks`(){

        val book = listOf<DetailsResponse>()
        val result = ResultsResponse("hard", book)

        response = ResponseModel(result)

        coEvery {  apiService.getResponseBooksList("list", "apikey") } returns ResponseModel(result)

        runTest {
            RepositoryImpl().getBooks()
        }

        coVerify { apiService.getResponseBooksList("list", "apikey") }
    }