package com.dio.mybookslist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.R
import com.dio.mybookslist.Model.data.model.BooksModel
import com.dio.mybookslist.databinding.ModelbookItemBinding
import com.squareup.picasso.Picasso

class AdapterListBooks(val lista: MutableList<BooksModel>, var onItemClickListener : ((book: BooksModel)-> Unit)) : RecyclerView.Adapter<BooksHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
//        val view = BooksHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.modelbook_item, parent, false),
//            onItemClickListener
//        )

        val binding = ModelbookItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return BooksHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        val listaPosition = lista[position]
        holder.bindView(listaPosition)
    }

    override fun getItemCount(): Int {
        Log.d("item count", lista.count().toString())
        return lista.count()
    }
}

class BooksHolder(private val binding: ModelbookItemBinding , var onItemClickListener : ((book: BooksModel)-> Unit)) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindView(book: BooksModel) {
        val setTitulo = binding.tvTitulo
        val setAutor = binding.tvAutor
        val setImage = binding.imvBookImage

        setTitulo.text = book.titulo
        setAutor.text = book.autor
        Picasso.get().load(book.imagem).fit().into(setImage)

        itemView.setOnClickListener {
            onItemClickListener.invoke(book)
        }
    }
}
