package com.dio.mybookslist.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.data.service.BaseAPi
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.repository.BooksRepostitory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel(private val repository: BooksRepostitory) : ViewModel() {

    private val apiKey: String
        get() = BuildConfig.API_KEY

    private var url_categories = "hardcover-fiction"

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    private val _books: MutableLiveData<List<BooksModel>> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Throwable> = MutableLiveData()


    val books: LiveData<List<BooksModel>> get() = _books
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Throwable> get() = _error

    fun getBooksList(){
        viewModelScope.launch {
            _loading.value = true
            try{
                _books.value = repository.getBooks(url_categories, apiKey)
                _loading.value= false
            } catch (t: Throwable){
                _books.value = emptyList()
                _loading.value = false
            }finally {
                _loading.value = false
            }
        }
    }





//    private val booksLiveData: MutableLiveData<List<BooksModel>>
//        get() = MutableLiveData()
//    val booksList: LiveData<List<BooksModel>>
//        get() = booksLiveData
//
//    private val apiKey: String
//        get() = BuildConfig.API_KEY
//
//    private var url_categories = "hardcover-fiction"
//
//    fun getBooks() {
//        BaseAPi.SERVICE_BOOK.getResponseBooksList(apiKey, url_categories).enqueue(object :
//            Callback<ResponseModel> {
//            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//                if (response.isSuccessful) {
//                    val books: MutableList<BooksModel> = mutableListOf()
//
//                    response.body()?.let { response ->
//                        try {
//                            // varial que indica o caminho no POJO
//                            val resultsBody = ArrayList(response?.results?.books)
//                            // Capturando linha por linha da resposta
//                            for (i in resultsBody) {
//                                val book: BooksModel = BooksModel(
//                                    titulo = i.title,
//                                    autor = i.author,
//                                    descricao = i.description,
//                                    imagem = i.book_image,
//                                    editora = i.publisher,
//                                    rank = i.rank
//                                )
//
//                                books.add(book)
//                            }
//                            booksLiveData.value = books
//                        } finally { }
//                    }
//                }
//            }
//                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//
//                }
//            })
//        }
}