package com.dio.mybookslist.data.model

import com.dio.mybookslist.R
import com.google.firebase.auth.FirebaseAuth

class LoginModel {

    private fun LoginUsuario() {

        var email = R.id.edtx_email.toString()
        var password = R.id.edtx_senha.toString()
//        var message = findViewById<TextView>(R.id.tv_error_message)

        if (email.isEmpty() || password.isEmpty()) {
//            message.setText("Coloque o seu e-mail e sua senha")
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
//                        Toast.makeText(this, "Login Efetuado com Sucesso!", Toast.LENGTH_SHORT)
//                            .show()
//                        AbrirTelaPrincipal()
                    }
                }.addOnFailureListener {
                    var erro = it

                    when {
//                        erro is FirebaseAuthInvalidCredentialsException -> message.setText("E-mail ou Senha incorretos!")
//                        erro is FirebaseNetworkException -> message.setText("Sem conexão com a internet!")
//                        else -> message.setText("Erro ao logar usuário!")
                    }
                }
        }
    }

    private fun VerificarUsuarioLogado() {
        val usurioAtual = FirebaseAuth.getInstance().currentUser

        if (usurioAtual != null) {
//            AbrirTelaPrincipal()
        }
    }

//    private fun AbrirTelaPrincipal() {
//        var intent = Intent(this, FragmentActivity::class.java)
//        startActivity(intent)
//
//    }

}