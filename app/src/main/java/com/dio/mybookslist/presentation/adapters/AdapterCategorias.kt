package com.dio.mybookslist.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dio.mybookslist.R
import com.dio.mybookslist.data.model.CategoriasModel
import com.dio.mybookslist.data.model.CategoriesDetails
import com.dio.mybookslist.databinding.ModelCategorieItemBinding

class AdapterCategories(): RecyclerView.Adapter<CatgoriesHolder>() {

    var categories = ArrayList<CategoriesDetails>()

    fun setUpdateData(categories: ArrayList<CategoriesDetails>){
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatgoriesHolder {
        val binding = ModelCategorieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatgoriesHolder(binding)
    }

    override fun onBindViewHolder(holder: CatgoriesHolder, position: Int) {
        holder.bind(categories.get(position))
    }

    override fun getItemCount(): Int {
         return categories.size
    }
}

class CatgoriesHolder(val binding: ModelCategorieItemBinding): RecyclerView.ViewHolder(binding.root) {

    val setCategorieTitle = binding.tvTitleCategorie

    fun bind(categorie : CategoriesDetails) {

        setCategorieTitle.text = categorie.list_name
    }
}

//class AdapterCategorias (val lista: MutableList<CategoriasModel>, var onItemClickListener : ((categoria: CategoriasModel)-> Unit)) : RecyclerView.Adapter<CategoriasHolder>(){
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
//        val view = CategoriasHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.model_categorie_item, parent, false),
//            onItemClickListener
//        )
//        return view
//    }
//
//    override fun onBindViewHolder(holder: CategoriasHolder, position: Int) {
//        val listaPosition = lista[position]
//        holder.bindView(listaPosition)
//    }
//
//    override fun getItemCount(): Int = lista.count()
//}
//
//class CategoriasHolder (itemVIew: View, var onItemClickListener : ((categoria: CategoriasModel)-> Unit)): RecyclerView.ViewHolder(itemVIew){
//
//    fun bindView(categoria: CategoriasModel){
//        val setNomeCategoria = itemView.findViewById<TextView>(R.id.tv_nome_categoria)
//        val getCategoriaUrl: String = categoria.list_nome_url
//
//        setNomeCategoria.text = categoria.lista_nome
//
//        itemView.setOnClickListener {
//            onItemClickListener.invoke(categoria)
//            Log.d("URL :", getCategoriaUrl.toString())
//        }
//    }
//}
