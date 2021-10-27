package com.dio.mybookslist.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.BuildConfig
import com.dio.mybookslist.R
import com.dio.mybookslist.R.id.rv_categorias_list
import com.dio.mybookslist.data.ApiServiceCategoriasList
import com.dio.mybookslist.data.BaseAPi
import com.dio.mybookslist.data.model.CategoriasModel
import com.dio.mybookslist.data.model.CategoriasResponse
import com.dio.mybookslist.ui.adapters.AdapterCategorias
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create


private lateinit var categoriasAdapter: AdapterCategorias

class CategoriasActivity : Fragment() {

    private val apiKey: String
        get() = BuildConfig.API_KEY

    companion object {
        fun newInstance(): CategoriasActivity {
            val fragmentHome = CategoriasActivity()
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
        return inflater.inflate(R.layout.activity_categorias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getApiResponse()
    }

    private fun getApiResponse() {
        var categoriasRecyclerView: RecyclerView = requireView().findViewById(rv_categorias_list)
        val categoriasArray = arrayListOf<CategoriasModel>()
        
        getAdapter(categoriasArray, categoriasRecyclerView)
               
        val callback = setApiCall()

        callback.enqueue(object: Callback<CategoriasResponse>{
            override fun onResponse(
                call: Call<CategoriasResponse>, response: Response<CategoriasResponse>
            ) {
                if (response.isSuccessful && response != null){

                    val categoriasColetion : MutableList<CategoriasModel> = mutableListOf()

                    response.body()?.let { response ->
                        try {
                            for (i in response.results){
                                val categoria: CategoriasModel = CategoriasModel(
                                    lista_nome = i.list_name,
                                    list_nome_url = i.list_name_encoded
                                )

                                categoriasArray.clear()
                                categoriasColetion.add(categoria)
                                categoriasArray.addAll(categoriasColetion)
                                categoriasAdapter.notifyDataSetChanged()
                            }
                        }finally {}
                    }
                }
            }

            override fun onFailure(call: Call<CategoriasResponse>, t: Throwable) {

            }

        })
    }

    private fun setApiCall(): Call<CategoriasResponse> {
        val retrofitClient = BaseAPi
            .getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/")
        val endPoints = retrofitClient.create(ApiServiceCategoriasList::class.java)
        val callback = endPoints.getResponseCategoriasList(apiKey)
        return callback
    }

    private fun getAdapter(
        categoriasArray: ArrayList<CategoriasModel>,
        categoriasRecyclerView: RecyclerView
    ) {
        categoriasAdapter = AdapterCategorias(categoriasArray, { categoria ->
//            val intent = Intent(this, ListActivity::class.java)
            val params = Bundle()
            val url = categoria.list_nome_url.toString()
            val navListActivity = ListActivity()
            navListActivity.arguments
            params.putString("urlCategories", url)
            fragmentManager?.beginTransaction()?.replace(categoria.list_nome_url.toInt(), navListActivity)?.commit()
//            intent?.putExtras(params)
//            startActivity(intent)
        })
        categoriasRecyclerView.adapter = categoriasAdapter
        categoriasRecyclerView.layoutManager =
            GridLayoutManager(context, 2)
    }

    private fun Intent(categoriasActivity: CategoriasActivity, java: Class<ListActivity>): Intent? {
        return Intent()
    }


}
