package com.dio.mybookslist.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.dio.mybookslist.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val detalheTitulo = intent.getStringExtra(EXTRA_TITULO)
        val detalheAutor = intent.getStringExtra(EXTRA_AUTOR)
        val detalheDescricao = intent.getStringExtra(EXTRA_DESCRICAO)
        val detalheImagem = intent.getStringExtra(EXTRA_IMAGEM)
        val detalheEditora = intent.getStringExtra(EXTRA_EDITORA)
        val detalheRank = intent.getIntExtra(EXTRA_RANK,0)


        val detalheTituloView = findViewById<TextView>(R.id.tv_detalhe_titulo)
        val detalheAutorView = findViewById<TextView>(R.id.tv_detalhe_autor_texto)
        val detalheDescricaoView = findViewById<TextView>(R.id.tv_detalhe_descricao_texto)
        val detalherEditoraView = findViewById<TextView>(R.id.tv_detalhe_editora_texto)
        val detalheImagemView = findViewById<ImageView>(R.id.imv_deatlhe_bookImage)
        val detalheFundo = findViewById<ImageView>(R.id.imv_background_detalhes)
//        val detalheRankView = findViewById<TextView>(R.id.tv_detalhe_nota_valor)


        detalheTituloView.text = detalheTitulo
        detalheAutorView.text = detalheAutor
        detalheDescricaoView.text = detalheDescricao
        detalherEditoraView.text = detalheEditora
//        detalheRankView.text = detalheRank.toString()
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

        fun getStartIntent(context: Context, titulo: String, autor: String, descricao: String, editora: String, imagem: String, rank: Int): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
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