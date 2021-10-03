package com.dio.mybookslist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.R
import com.dio.mybookslist.data.ApiService
import com.dio.mybookslist.data.BaseAPi
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var booksAdapter: AdapterListBooks

class ListActivity : AppCompatActivity() {

    private val apiKey: String
        get() = BuildConfig.API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getApiResponse()


        // teste do recycler
//        val booksLiveData: MutableList<BooksModel> = ArrayList()
//        for( c in 0..4){
//            val booksTest = BooksModel("", "", "")
//            booksTest.titulo = "Titulo $c"
//
//            booksLiveData.add(booksTest)
//        }

    }


    private fun openDetailsBook() {
        var intent = Intent(this, BookDetailsActivity::class.java)
        startActivity(intent)
    }

    private fun getAdapter(
        booksArray: ArrayList<BooksModel>,
        booksListRecyclerView: RecyclerView
    ) {
        booksAdapter = AdapterListBooks(booksArray,{book ->
            val intent: Intent = BookDetailsActivity.getStartIntent(this, book.titulo, book.autor, book.descricao, book.editora, book.imagem, book.rank)
            startActivity(intent)
        })
        booksListRecyclerView.adapter = booksAdapter
        booksListRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun getApiResponse() {
        val booksListRecyclerView: RecyclerView = findViewById(R.id.rv_books_list)
        val booksArray = arrayListOf<BooksModel>()

        // vinculando o adapter
        getAdapter(booksArray, booksListRecyclerView)

        // chamada da API
        val callback = setApiCall()

        callback.enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//              evitando resposta nula
                if (response.isSuccessful && response != null){
                    // criando lista mutavel de item
                    val booksColetion : MutableList<BooksModel> = mutableListOf()

                    response.body()?.let { response ->
                        try {
                            // varial que indica o caminho no POJO
                            val resultsBody = ArrayList(response?.results?.books)
                            // Capturando linha por linha da resposta
                            for ( i in resultsBody){
                                val book : BooksModel = BooksModel(
                                    titulo = i.title,
                                    autor= i.author,
                                    descricao = i.description,
                                    imagem = i.book_image,
                                    editora = i.publisher,
                                    rank = i.rank
                                )
                                //configurando resposta na lista mutavel
                                booksArray.clear()
                                booksColetion.add(book)
                                booksArray.addAll(booksColetion)
                                booksAdapter.notifyDataSetChanged()
//                                Log.d("Teste 2 ", book.toString())
                            }

                        }finally { }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@ListActivity, t.message, Toast.LENGTH_SHORT).show()
//                Log.d("ERROR", t.message.toString())
            }
        })
    }

    private fun setApiCall(): Call<ResponseModel> {
        val retorfitClient = BaseAPi
            .getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/current/")
        val endPoint = retorfitClient.create(ApiService::class.java)
        val callback = endPoint.getResponse(apiKey)
        return callback
    }



    // Funcao de teste do recycler
//    private fun testFakeBooks(): List<BooksModel> {
//        return listOf(
//            BooksModel("Duna", "autor 1", "", "",""),
//            BooksModel("Duna", "autor 1", "","",""),
//            BooksModel("Duna", "autor 1", "","",""),
//            BooksModel("Duna", "autor 1", "","","")
//        )
//
//    }
}
