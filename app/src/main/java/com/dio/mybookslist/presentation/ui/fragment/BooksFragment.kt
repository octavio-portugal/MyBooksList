package com.dio.mybookslist.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.R
import com.dio.mybookslist.databinding.BooksFragmentBinding
import com.dio.mybookslist.presentation.AdapterListBooks
import com.dio.mybookslist.presentation.ui.BookDetailsActivity
import com.dio.mybookslist.presentation.ui.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//private lateinit var booksAdapter: AdapterListBooks

class BooksFragment : Fragment() {


    /**
     * Usa o Koin para injetar a dependência do ViewModel
     */
//    private lateinit var viewModel: BooksViewModel
    private val viewModel: BooksViewModel by viewModel()
    private val binding: BooksFragmentBinding
    by lazy {
        BooksFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initBinding()
        initRecyclerView()
        initViewModel()
        return binding.root

    }

    private fun initViewModel() {
//         viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

    }

    private fun initRecyclerView() {

        val adapter = AdapterListBooks(mutableListOf<BooksModel>(), { book ->
            val intent = BookDetailsActivity.getStartIntent(this, book.titulo, book.autor, book.descricao, book.editora, book.imagem, book.rank)
            this.startActivity(intent)})

        binding.rvBooksList.adapter= adapter
        binding.rvBooksList.layoutManager = LinearLayoutManager(this.context)
    }

    /**
     * Esse método faz a inicialização do DataBinding.
     * O arquivo XML possui uma variável viewModel, que precisa
     * ser vinculada ao ViewModel instanciado. Também é preciso
     * atribuir um LifeCycleOwner para que os bindings de live data
     * funcionem.
     */
    private fun initBinding() {
        binding.viewModelBooks = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


    /**
     * Esse companion object é código boilerplate que provavelmente
     * não será usado.
     */
    companion object {
        fun newInstance() = BooksFragment()
    }

}

