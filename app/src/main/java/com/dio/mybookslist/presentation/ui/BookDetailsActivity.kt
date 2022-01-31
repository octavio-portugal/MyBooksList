package com.dio.mybookslist.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.dio.mybookslist.R
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

        val detailTitle = intent.getStringExtra(EXTRA_TITlE)
        val detailAuthor = intent.getStringExtra(EXTRA_AUTHOR)
        val detailDescription = intent.getStringExtra(EXTRA_DESCRIPTION)
        val detailImage = intent.getStringExtra(EXTRA_IMAGE)
        val detailPublisher = intent.getStringExtra(EXTRA_PUBLISHER)

        val detailTitleView = binding.tvDetalheTitulo
        val detailAuthorView = binding.tvDetalheAutorTexto
        val detailDescriptionView = binding.tvDetalheDescricaoTexto
        val detailPublisherView = binding.tvDetalheEditoraTexto
        val detailImageView = binding.imvDeatlheBookImage
        val detailBackGroundImage = binding.imvBackgroundDetalhes

        detailTitleView.text = detailTitle
        detailAuthorView.text = detailAuthor
        detailDescriptionView.text = detailDescription
        detailPublisherView.text = detailPublisher
        Picasso.get().load(detailImage).fit().into(detailImageView)
        Picasso.get().load(detailImage).transform(BlurTransformation(this, 25, 1)).fit()
            .into(detailBackGroundImage)
    }

    companion object {
        private const val EXTRA_TITlE = "EXTRA_TITLE"
        private const val EXTRA_AUTHOR = "EXTRA_AUTHOR"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        private const val EXTRA_IMAGE = "EXTRA_IMAGE"
        private const val EXTRA_PUBLISHER = "EXTRA_PUBLISHER"
        private const val EXTRA_RANK = "EXTRA_RANK"

        fun getStartIntent(
            context: BooksFragment,
            title: String,
            author: String,
            description: String,
            publisher: String,
            book_image: String
        ): Intent {
            return Intent(context.context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITlE, title)
                putExtra(EXTRA_AUTHOR, author)
                putExtra(EXTRA_DESCRIPTION, description)
                putExtra(EXTRA_IMAGE, book_image)
                putExtra(EXTRA_PUBLISHER, publisher)
//                putExtra(EXTRA_RANK,rank)
            }
        }
    }
}