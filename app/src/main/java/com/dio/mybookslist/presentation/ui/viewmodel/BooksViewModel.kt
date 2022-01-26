package com.dio.mybookslist.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.service.BaseAPi
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    private val booksLiveData: MutableLiveData<List<BooksModel>>
        get() = MutableLiveData()
    val booksList: LiveData<List<BooksModel>>
        get() = booksLiveData

    private val apiKey: String
        get() = BuildConfig.API_KEY

    private var url_categories = "hardcover-fiction"

    fun getBooks() {
        BaseAPi.serviceBook.getResponseBooksList(apiKey, url_categories).enqueue(object :
            Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    val books: MutableList<BooksModel> = mutableListOf()

                    response.body()?.let { response ->
                        try {
                            // varial que indica o caminho no POJO
                            val resultsBody = ArrayList(response?.results?.books)
                            // Capturando linha por linha da resposta
                            for (i in resultsBody) {
                                val book: BooksModel = BooksModel(
                                    titulo = i.title,
                                    autor = i.author,
                                    descricao = i.description,
                                    imagem = i.book_image,
                                    editora = i.publisher,
                                    rank = i.rank
                                )

                                books.add(book)
                            }
                            booksLiveData.value = books
                        } finally { }
                    }
                }
            }
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {

                }
            })
        }
    }