package com.example.registro_ganadero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registo : AppCompatActivity() {

    private lateinit var TxtNombre:EditText
    private lateinit var TxtApellido:EditText
    private lateinit var TxtCorreo:EditText
    private lateinit var TxtContrasena:EditText
    private lateinit var dbReference:DatabaseReference
    private lateinit var database:FirebaseDatabase
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo)
        TxtNombre=findViewById(R. id. TxtNombre)
        TxtApellido=findViewById(R. id. TxtApellido)
        TxtCorreo=findViewById(R. id. TxtCorreo)
        TxtContrasena=findViewById(R. id. TxtContrasena)

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference=database.reference.child("Usuario")
    }


    fun Registro (view: View) {
        CreateNewAccount()
    }

    private fun CreateNewAccount(){
        val nombre:String=TxtNombre.text.toString()
        val apellido:String=TxtApellido.toString()
        val correo:String=TxtCorreo.toString()
        val contrasena:String=TxtContrasena.toString()

        if(!TextUtils.isEmpty (nombre)&& !TextUtils.isEmpty (apellido)&& !TextUtils.isEmpty (correo)&& !TextUtils.isEmpty (contrasena) ){

            auth.createUserWithEmailAndPassword(correo,contrasena)
                .addOnCompleteListener(this) {
                    task ->

                    if(task.isComplete){
                        val user:FirebaseUser?=auth.currentUser
                        verifyEmail(user)

                        val userDB=dbReference.child(user?.uid!!)

                        userDB.child("Nombre").setValue(nombre)
                        userDB.child("Apellido").setValue(apellido)
                        action()
                    }
                }
        }

    }

        private fun action(){
            startActivity(Intent(this , MainActivity::class.java))
        }
        private fun verifyEmail(user:FirebaseUser?){
            user?.sendEmailVerification()
                ?.addOnCompleteListener(this){
                    task ->

                    if(task.isComplete) {
                        Toast.makeText(this, "Correo Enviado Correctamente", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Error al Enviado Correo", Toast.LENGTH_LONG).show()
                    }
                }

        }
}
