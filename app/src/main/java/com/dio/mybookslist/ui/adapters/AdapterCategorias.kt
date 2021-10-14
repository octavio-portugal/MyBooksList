package com.dio.mybookslist.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.ui.BooksHolder

class AdapterCategorias (val lista: MutableList<CategoriaModel>, var onItemClickListener : ((book: CategoriasModel)-> Unit)) : RecyclerView.Adapter<CategoriasHolder>(){

}