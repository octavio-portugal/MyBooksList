package com.dio.mybookslist.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dio.mybookslist.R
import com.dio.mybookslist.databinding.BooksFragmentBinding
import com.dio.mybookslist.presentation.AdapterListBooks
import com.dio.mybookslist.presentation.ui.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//private lateinit var booksAdapter: AdapterListBooks

class BooksFragment : Fragment() {


    /**
     * Usa o Koin para injetar a dependência do ViewModel
     */
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
        return binding.root
    }

    private fun initRecyclerView() {

        val adapter = AdapterListBooks()
        binding.rvBooksList.adapter= adapter




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

