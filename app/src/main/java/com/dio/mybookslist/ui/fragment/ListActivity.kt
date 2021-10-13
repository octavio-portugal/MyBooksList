package com.dio.mybookslist.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.convertTo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.R
import com.dio.mybookslist.R.id.*
import com.dio.mybookslist.data.ApiService
import com.dio.mybookslist.data.BaseAPi
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.ui.AdapterListBooks
import com.dio.mybookslist.ui.BookDetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var booksAdapter: AdapterListBooks

class ListActivity : Fragment() {

    private val apiKey: String
        get() = BuildConfig.API_KEY

    companion object {
        fun newInstance(): ListActivity {
            val fragmentHome = ListActivity()
            val arguments = Bundle()
            fragmentHome.arguments = arguments
            return fragmentHome
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun getApiResponse() {
        var booksListRecyclerView: RecyclerView = requireView().findViewById(rv_books_list)
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
//                Toast.makeText(this@ListActivity, t.message, Toast.LENGTH_SHORT).show()
//                Log.d("ERROR", t.message.toString())
            }
        })
    }

    private fun setApiCall(): Call<ResponseModel> {
        val retorfitClient = BaseAPi
            .getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/current/")
        val endPoint = retorfitClient.create(ApiService::class.java)
        val callback = endPoint.getResponse("hardcover-fiction", apiKey)
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
