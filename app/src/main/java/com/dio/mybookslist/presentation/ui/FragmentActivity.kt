package com.dio.mybookslist.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dio.mybookslist.R
import com.dio.mybookslist.databinding.FragmentActivityBinding
import com.dio.mybookslist.presentation.ui.fragment.BooksFragment
import com.dio.mybookslist.presentation.ui.fragment.CategoriasActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("DEPRECATION")
class FragmentActivity : AppCompatActivity() {
    private var Content: FrameLayout? = null

    private lateinit var binding: FragmentActivityBinding

    private var mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.nav_library -> {
                    val fragment = BooksFragment.newInstance()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_categories -> {
                    val fragment = CategoriasActivity.newInstance()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        Content = binding.content

        var bottom_menu = binding.bottomMenu
        bottom_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}