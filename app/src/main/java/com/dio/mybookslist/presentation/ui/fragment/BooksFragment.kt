package com.dio.mybookslist.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.DetailsResponse
import com.dio.mybookslist.data.model.ResponseModel
import com.dio.mybookslist.data.model.returnArray
import com.dio.mybookslist.databinding.BooksFragmentBinding
import com.dio.mybookslist.presentation.AdapterListBooks
import com.dio.mybookslist.presentation.ui.viewmodel.BooksListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BooksFragment : Fragment() {

    private val viewModel: BooksListViewModel by viewModel()
    private val binding: BooksFragmentBinding
            by lazy {
                BooksFragmentBinding.inflate(layoutInflater)
            }
    private lateinit var recyclerAdapter: AdapterListBooks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        binding.viewModelBooks = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRecyclerView() {


         val recyclerAdapter = AdapterListBooks()
//        { book ->
//            val intent: Intent = BookDetailsActivity.getStartIntent(
//                this@BooksFragment, book.title, book.author, book.description, book.publisher, book.book_image)
//            if (intent != null) {
//                startActivity(intent)
//            } else {
//                Log.e("error", "deu errado")
//            }
//        })

        binding.rvBooksList.adapter = recyclerAdapter
        binding.rvBooksList.layoutManager = LinearLayoutManager(this.context)
    }

    private fun initViewModel() {
        viewModel
        viewModel.makeApiCall()
        viewModel.getBooksListObserver().observe(viewLifecycleOwner, Observer<BooksModel> {
            if (it != null) {
                val books = ArrayList<BooksModel>()
                recyclerAdapter.setUpdateData(it.returnArray(books))
            } else {
                Toast.makeText(this.context, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        fun newInstance() = BooksFragment()
    }
}

