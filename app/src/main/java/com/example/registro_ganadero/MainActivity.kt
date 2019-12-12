package com.example.registro_ganadero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var TxtCorreo: EditText
    private lateinit var TxtContrasena: EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TxtCorreo=findViewById(R. id. TxtCorreo)
        TxtContrasena=findViewById(R. id. TxtContrasena)
        auth= FirebaseAuth.getInstance()


    }

    fun Registo(view: View){
        startActivity(Intent( this,RegistroGanado::class.java))
    }
    fun login(view: View){
        loginUser()

    }
    private fun loginUser(){
        val user:String=TxtCorreo.text.toString()
        val password:String=TxtContrasena.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {

            auth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        action()
                    }else{
                        Toast.makeText(this, "Error en la Autenticación", Toast.LENGTH_LONG).show()

                    }
                }
        }
    }
    private fun action (){
        startActivity(Intent(this,PantallaPrincipal::class.java ))
    }
    fun registrarse(view: View){
        startActivity(Intent(this, Registo::class.java))
    }


    }

