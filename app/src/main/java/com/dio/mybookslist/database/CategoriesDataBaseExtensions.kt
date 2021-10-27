package com.dio.mybookslist.database

import android.content.ContentValues
import com.dio.mybookslist.data.model.CategoriasModel

//fun CategoriasDataBase.insertCategoriesDataBaseUrls(item: CategoriasModel): Long{
//
//    val categoriUrls = writableDatabase.insert("TB_CATEGORIES", null, ContentValues().apply {
//        put("CATEGORIE", item.list_nome_url)
//    })
//    return categoriUrls
//}
//
//fun CategoriasDataBase.readCategoriesDataBaseUrls() {
//
//    val selectedSql = "SELECT * FROM TB_CATEGORIES"
//
//    val cursor = readableDatabase.rawQuery(selectedSql, null)
//
//    val returnUrl = CategoriasModel("" ,cursor.getString(cursor.getColumnIndex("CATEGORIE_URLS")))
//
//}