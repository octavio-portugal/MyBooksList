package com.dio.mybookslist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.dio.mybookslist.R
import com.dio.mybookslist.ui.fragment.ListActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentActivity : AppCompatActivity(){

    private val Content: FrameLayout? = null
    private var mOnNvaigationItemSelectedListiner= BottomNavigationView.OnNavigationItemSelectedListener{item ->

        when(item.itemId){
            R.id.nav_library -> {
                val fragment = ListActivity.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_categories -> {
                val fragment = Search.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Content = content
        bottom_menu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private fun addFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content,fragment,fragment.javaClass.simpleName)
            .commit()
    }
}