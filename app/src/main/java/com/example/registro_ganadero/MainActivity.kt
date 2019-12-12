package com.example.registro_ganadero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var TxtCorreo: EditText
    private lateinit var TxtContraseña: EditText

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TxtCorreo=findViewById(R. id. TxtCorreoElectronicoR)
        TxtContraseña=findViewById(R. id. TxtContraseñaR)
        auth= FirebaseAuth.getInstance()


    }

    fun Registo(view: View){
        startActivity(Intent( this,RegistroGanado::class.java))
    }
    fun MainActivity(view: View){
        loginUser()

    }
    private fun loginUser(){
        val user:String=TxtCorreo.text.toString()
        val password:String=TxtContraseña.text.toString()

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
        startActivity(Intent(this,MainActivity::class.java ))
    }
}
