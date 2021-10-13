package com.dio.mybookslist.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dio.mybookslist.R

class CategoriasActivity : Fragment() {

    companion object {
        fun newInstance(): CategoriasActivity {
            val fragmentHome = CategoriasActivity()
            val arguments = Bundle()
            fragmentHome.arguments = arguments
            return fragmentHome
        }

         fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.activity_categorias2, container, false)
        }

        fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        }
    }
}