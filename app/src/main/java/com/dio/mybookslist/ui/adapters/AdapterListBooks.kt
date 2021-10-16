package com.dio.mybookslist.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.R
import com.dio.mybookslist.data.model.BooksModel
import com.squareup.picasso.Picasso

class AdapterListBooks(val lista: MutableList<BooksModel>, var onItemClickListener : ((book: BooksModel)-> Unit)) : RecyclerView.Adapter<BooksHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        val view = BooksHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.modelbook_item, parent, false),
            onItemClickListener
        )
        return view
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

class BooksHolder(itemVIew: View , var onItemClickListener : ((book: BooksModel)-> Unit)) : RecyclerView.ViewHolder(itemVIew) {
    fun bindView(book: BooksModel) {
        val setTitulo = itemView.findViewById<TextView>(R.id.tv_titulo)
        val setAutor = itemView.findViewById<TextView>(R.id.tv_autor)
        val setImage = itemView.findViewById<ImageView>(R.id.imv_bookImage)

        setTitulo.text = book.titulo
        setAutor.text = book.autor
        Picasso.get().load(book.imagem).fit().into(setImage)

        itemView.setOnClickListener {
            onItemClickListener.invoke(book)
        }
    }
}
