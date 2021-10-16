package com.dio.mybookslist.ui.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.R
import com.dio.mybookslist.data.model.BooksModel
import com.dio.mybookslist.data.model.CategoriasModel
import com.dio.mybookslist.ui.BooksHolder

class AdapterCategorias (val lista: MutableList<CategoriasModel>, var onItemClickListener : ((categoria: CategoriasModel)-> Unit)) : RecyclerView.Adapter<CategoriasHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
        val view = CategoriasHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.modelo_categoria_item, parent, false),
            onItemClickListener
        )
        return view
    }

    override fun onBindViewHolder(holder: CategoriasHolder, position: Int) {
        val listaPosition = lista[position]
        holder.bindView(listaPosition)
    }

    override fun getItemCount(): Int = lista.count()

}

class CategoriasHolder (itemVIew: View, var onItemClickListener : ((categoria: CategoriasModel)-> Unit)): RecyclerView.ViewHolder(itemVIew){
    fun bindView(categoria: CategoriasModel){
        val setNomeCategoria = itemView.findViewById<TextView>(R.id.tv_nome_categoria)

        setNomeCategoria.text = categoria.lista_nome

        itemView.setOnClickListener {
            onItemClickListener.invoke(categoria)
        }
    }
}
