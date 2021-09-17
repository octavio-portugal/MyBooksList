package com.dio.mybookslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.data.ApiService
import com.dio.mybookslist.data.BaseAPi
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.presenter.AdapterListBooks
import com.dio.mybookslist.presenter.BookDetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import androidx.appcompat.view.menu.MenuView.ItemView as ItemView


private lateinit var booksAdapter: AdapterListBooks

class MainActivity : AppCompatActivity() {
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
                                    imagem = i.book_image
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
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
//                Log.d("ERROR", t.message.toString())
            }
        })
    }

    private fun setApiCall(): Call<ResponseModel> {
        val retorfitClient = BaseAPi
            .getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/current/")
        val endPoint = retorfitClient.create(ApiService::class.java)
        val callback = endPoint.getResponse()
        return callback
    }

    private fun getAdapter(
        booksArray: ArrayList<BooksModel>,
        booksListRecyclerView: RecyclerView
    ) {
        booksAdapter = AdapterListBooks(booksArray,{book ->
            val intent: Intent = BookDetailsActivity.getStartIntent()
        })
        booksListRecyclerView.adapter = booksAdapter
        booksListRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    // Funcao de teste do recycler
    private fun testFakeBooks(): List<BooksModel> {
        return listOf(
            BooksModel("Duna", "autor 1", "", ""),
            BooksModel("Duna", "autor 1", "",""),
            BooksModel("Duna", "autor 1", "",""),
            BooksModel("Duna", "autor 1", "","")
        )

    }
}
