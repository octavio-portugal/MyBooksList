package com.dio.mybookslist.ui

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dio.mybookslist.R
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

@Suppress("DEPRECATION")
class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        val btn_entrar = findViewById<Button>(R.id.btn_entrar)

        VerificarUsuarioLogado()

        btn_entrar.setOnClickListener {

            LoginUsuario()
        }
    }

    private fun LoginUsuario() {

        var email = findViewById<EditText>(R.id.edtx_email).text.toString()
        var senha = findViewById<EditText>(R.id.edtx_senha).text.toString()
        var mensagens = findViewById<TextView>(R.id.tv_error_message)

        if (email.isEmpty() || senha.isEmpty()) {
            mensagens.setText("Coloque o seu e-mail e sua senha")
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        Toast.makeText(this, "Login Efetuado com Sucesso!", Toast.LENGTH_SHORT)
                            .show()
                        AbrirTelaPrincipal()
                    }
                }.addOnFailureListener {
                    var erro = it

                    when {
                        erro is FirebaseAuthInvalidCredentialsException -> mensagens.setText("E-mail ou Senha incorretos!")
                        erro is FirebaseNetworkException -> mensagens.setText("Sem conexão com a internet!")
                        else -> mensagens.setText("Erro ao logar usuário!")
                    }
                }
        }
    }

    private fun VerificarUsuarioLogado() {
        val usurioAtual = FirebaseAuth.getInstance().currentUser

        if (usurioAtual != null) {
            AbrirTelaPrincipal()
        }
    }

    private fun AbrirTelaPrincipal() {
        var intent = Intent(this, com.dio.mybookslist.ui.ListActivity::class.java)
        startActivity(intent)
        finish()
    }

}
