package com.example.taller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.taller.model.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register_users.*
import java.lang.Error

class registerUsers : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference// ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_users)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
//cancelar
        btncancelar.setOnClickListener() {
            intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
            finish()
        }
btnsend.setOnClickListener(){
    crearCuenta()
}

    }


    fun crearCuenta() {
        var correo = txtEmail.text.toString()
        var passwd = txtpwd.text.toString()
        var cc=txtcedula.text.toString()
        var nombre=txtname.text.toString()
        var apellido=txtApellido.text.toString()
        //comparar que las contraseñas coincida
        //crear(correo,passwd,name,apellido,cedula)
            auth.createUserWithEmailAndPassword(correo, passwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        crear(correo,passwd,nombre,apellido,cc)
Toast.makeText(this,"Enhorabuena, se ha creado tu perfil",Toast.LENGTH_SHORT).show()
                        intent = Intent(this, MainActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                        startActivity(intent)
                        finish()
                    }else{
                        Log.w("esto son sus passwd1",task.exception)
                        Toast.makeText(this,"al parecer hay errores en los datos que has ingresado "+task.exception,Toast.LENGTH_LONG).show()
                    }
                }

    }
//funcion que almacena los datos en la base de datos
fun crear(correo:String,passwd:String,nomb:String,Ape:String,cedula:String){
var usuarios=usuario(correo,passwd,nomb,Ape,cedula)
    database.child("Perfiles").child(cedula).setValue(usuarios)
}


}
