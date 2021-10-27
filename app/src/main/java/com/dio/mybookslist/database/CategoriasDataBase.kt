package com.dio.mybookslist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//class CategoriasDataBase(
//    context: Context
//): SQLiteOpenHelper (context, "categorias.db", null, 1){
//    override fun onCreate(db: SQLiteDatabase?) {
//        val sql = """
//            CREATE TABLE TB_CATEGORIES(
//                CATEGORIE_URLS TEXT PRIMARY KEY
//            );
//        """.trimIndent()
//        db?.execSQL(sql)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db?.execSQL("DROP TABLE IF EXISTS TB_CATEGORIES")
//        onCreate(db)
//    }
//}