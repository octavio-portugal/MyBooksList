package com.dio.mybookslist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.R
import com.dio.mybookslist.data.model.CategoriasModel


class AdapterCategorias (val lista: MutableList<CategoriasModel>, var onItemClickListener : ((categoria: CategoriasModel)-> Unit)) : RecyclerView.Adapter<CategoriasHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
        val view = CategoriasHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.model_categorie_item, parent, false),
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
        val getCategoriaUrl: String = categoria.list_nome_url

        setNomeCategoria.text = categoria.lista_nome

        itemView.setOnClickListener {
            onItemClickListener.invoke(categoria)
            Log.d("URL :", getCategoriaUrl.toString())
        }
    }
}
