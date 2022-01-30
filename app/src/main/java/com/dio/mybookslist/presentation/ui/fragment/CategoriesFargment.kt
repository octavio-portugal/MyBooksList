package com.dio.mybookslist.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dio.mybookslist.data.model.CategoriesDetails
import com.dio.mybookslist.data.model.CategoriesResponse
import com.dio.mybookslist.databinding.CategoriesFragmentBinding
import com.dio.mybookslist.presentation.adapters.AdapterCategories
import com.dio.mybookslist.presentation.ui.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by viewModel()
    private val binding: CategoriesFragmentBinding
        by lazy { CategoriesFragmentBinding.inflate(layoutInflater)}
    private lateinit var recyclerAdapter: AdapterCategories


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initRecyclerView()
        initViewModel()

    }

    private fun initBinding() {
        binding.viewModelCategories = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRecyclerView() {
        recyclerAdapter = AdapterCategories()
        binding.rvCategoriesList.adapter = recyclerAdapter
        binding.rvCategoriesList.layoutManager = LinearLayoutManager(this.context)
    }

    private fun initViewModel(){
        viewModel.getCategoriesListObserver().observe(viewLifecycleOwner, Observer {
            if(it != null){
                val categories: CategoriesResponse
                recyclerAdapter.setUpdateData(it.results as ArrayList<CategoriesDetails>)
            }else{
                Toast.makeText(this.context, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
            viewModel.makeApiCall()
        })
    }

    companion object {
        fun newInstance() = BooksFragment()
    }


//    companion object {
//        fun newInstance(): CategoriesFragment {
//            val fragmentHome = CategoriesFragment()
//            val arguments = Bundle()
//            fragmentHome.arguments = arguments
//            return fragmentHome
//        }
//    }
//
//    override fun onCreateView(
//
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.activity_categorias, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        getApiResponse()
//    }
//
//    private fun getApiResponse() {
//        var categoriasRecyclerView: RecyclerView = requireView().findViewById(rv_categorias_list)
//        val categoriasArray = arrayListOf<CategoriasModel>()
//
//        getAdapter(categoriasArray, categoriasRecyclerView)
//
//        val callback = setApiCall()
//
//        callback.enqueue(object: Callback<CategoriasResponse>{
//            override fun onResponse(
//                call: Call<CategoriasResponse>, response: Response<CategoriasResponse>
//            ) {
//                if (response.isSuccessful && response != null){
//
//                    val categoriasColetion : MutableList<CategoriasModel> = mutableListOf()
//
//                    response.body()?.let { response ->
//                        try {
//                            for (i in response.results){
//                                val categoria: CategoriasModel = CategoriasModel(
//                                    lista_nome = i.list_name,
//                                    list_nome_url = i.list_name_encoded
//                                )
//
//                                categoriasArray.clear()
//                                categoriasColetion.add(categoria)
//                                categoriasArray.addAll(categoriasColetion)
//                                categoriasAdapter.notifyDataSetChanged()
//                            }
//                        }finally {}
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<CategoriasResponse>, t: Throwable) {
//
//            }
//        })
//    }
//
//    private fun setApiCall(): Call<CategoriasResponse> {
//        val retrofitClient = BaseAPi
//            .getRetrofitInstance("https://api.nytimes.com/svc/books/v3/lists/")
//        val endPoints = retrofitClient.create(ApiServiceCategorias::class.java)
//        val callback = endPoints.getResponseCategoriasList(apiKey)
//        return callback
//    }
//
//    private fun getAdapter(
//        categoriasArray: ArrayList<CategoriasModel>,
//        categoriasRecyclerView: RecyclerView
//    ) {
//        categoriasAdapter = AdapterCategorias(categoriasArray, { categoria ->
////            val intent = Intent(this, ListActivity::class.java)
//            val params = Bundle()
//            val url = categoria.list_nome_url.toString()
//            val navListActivity = BooksFragment()
//            navListActivity.arguments
//            params.putString("urlCategories", url)
//            fragmentManager?.beginTransaction()?.replace(categoria.list_nome_url.toInt(), navListActivity)?.commit()
////            intent?.putExtras(params)
////            startActivity(intent)
//        })
//        categoriasRecyclerView.adapter = categoriasAdapter
//        categoriasRecyclerView.layoutManager =
//            GridLayoutManager(context, 2)
//    }
//
//    private fun Intent(categoriasActivity: CategoriasActivity, java: Class<BooksFragment>): Intent? {
//        return Intent()
//    }
}
