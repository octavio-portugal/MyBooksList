package com.dio.mybookslist.presentation.ui

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dio.mybookslist.R
import com.dio.mybookslist.presentation.ui.fragment.BooksFragment
import com.dio.mybookslist.presentation.ui.fragment.CategoriasActivity
import com.dio.mybookslist.presentation.ui.fragment.ListActivity
import com.dio.mybookslist.presentation.ui.viewmodel.BooksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class FragmentActivity : AppCompatActivity() {
    private var Content: FrameLayout? = null

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
        setContentView(R.layout.fragment_activity)

        Content = findViewById(R.id.content)

        var bottom_menu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottom_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}