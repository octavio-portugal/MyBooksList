package com.dio.mybookslist.presenter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dio.mybookslist.R

class BookDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
    }

    companion object {
        private const val EXTRA_TITULO = "EXTRA_TITULO"
        private const val EXTRA_AUTOR = "EXTRA_AUTOR"
        private const val EXTRA_DESCRICAO = "EXTRA_DESCRICAO"
        fun getStartIntent(context: Context, titulo: String, autor: String, descricao: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITULO, titulo)
                putExtra(EXTRA_TITULO, titulo)
                putExtra(EXTRA_TITULO, titulo)

            }


        }
    }
}