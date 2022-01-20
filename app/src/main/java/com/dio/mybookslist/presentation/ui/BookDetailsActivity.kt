package com.dio.mybookslist.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.dio.mybookslist.databinding.ActivityBookDetailsBinding
import com.dio.mybookslist.presentation.ui.fragment.BooksFragment
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation

class BookDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityBookDetailsBinding.inflate(LayoutInflater.from(parent))
        setContentView(binding.root)

        //configura o recebimento dos dados do fragment, e ajusta as View corretas

        val detalheTitulo = intent.getStringExtra(EXTRA_TITULO)
        val detalheAutor = intent.getStringExtra(EXTRA_AUTOR)
        val detalheDescricao = intent.getStringExtra(EXTRA_DESCRICAO)
        val detalheImagem = intent.getStringExtra(EXTRA_IMAGEM)
        val detalheEditora = intent.getStringExtra(EXTRA_EDITORA)

        val detalheTituloView = binding.tvDetalheTitulo
        val detalheAutorView = binding.tvDetalheAutorTexto
        val detalheDescricaoView = binding.tvDetalheDescricaoTexto
        val detalherEditoraView = binding.tvDetalheEditoraTexto
        val detalheImagemView = binding.imvDeatlheBookImage
        val detalheFundo = binding.imvBackgroundDetalhes

        detalheTituloView.text = detalheTitulo
        detalheAutorView.text = detalheAutor
        detalheDescricaoView.text = detalheDescricao
        detalherEditoraView.text = detalheEditora
        Picasso.get().load(detalheImagem).fit().into(detalheImagemView)
        Picasso.get().load(detalheImagem).transform(BlurTransformation(this, 25,1)).fit().into(detalheFundo)
    }

    companion object {
        private const val EXTRA_TITULO = "EXTRA_TITULO"
        private const val EXTRA_AUTOR = "EXTRA_AUTOR"
        private const val EXTRA_DESCRICAO = "EXTRA_DESCRICAO"
        private const val EXTRA_IMAGEM = "EXTRA_IMAGEM"
        private const val EXTRA_EDITORA = "EXTRA_EDITORA"
        private const val EXTRA_RANK = "EXTRA_RANK"

        fun getStartIntent( context: BooksFragment,titulo: String, autor: String, descricao: String, editora: String, imagem: String, rank: Int): Intent {
            return Intent( context.context, BookDetailsActivity::class.java).apply{
                putExtra(EXTRA_TITULO, titulo)
                putExtra(EXTRA_AUTOR, autor)
                putExtra(EXTRA_DESCRICAO, descricao)
                putExtra(EXTRA_IMAGEM, imagem)
                putExtra(EXTRA_EDITORA, editora)
                putExtra(EXTRA_RANK,rank)
            }
        }
    }
}