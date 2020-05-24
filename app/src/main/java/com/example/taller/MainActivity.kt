package com.example.taller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var door: AuthStateListener
    lateinit var auth: FirebaseAuth// ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables que son para iniciar sesion
        val dbReference: DatabaseReference
        val db: FirebaseDatabase
        auth = FirebaseAuth.getInstance()
        db= FirebaseDatabase.getInstance()
        dbReference=db.reference.child("Perfiles")

//----------------------------------------------------------------------------------------//-----------------
        var user:String
        var pwd:String

        //Ahora iniciamos sesion con el boton
        Login.setOnClickListener(){

            //aqui estoy tomando los valores que he escrito en el formulario inicial, para iniciar sesion
            user= txtUser.text.toString()
            pwd=Txtpwd.text.toString()
//una pequeña validacion para iniciar sesion
            try{
                if(user.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(this,"usuario o contraseña vacias",Toast.LENGTH_SHORT).show()}
                else{
login(user,pwd)
                }
            }catch (e:Error){
Toast.makeText(this,"Al parecer tienes usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show()
            }

        }
//-------------------------------------------------------------------------------------------
        //con este algoritmo, mantengo las credenciales para que mi aplicacion no cierre sesion cada vez que cierre la aplicacion
        door= FirebaseAuth.AuthStateListener {
            var usuario=FirebaseAuth.getInstance().currentUser
            if(usuario!=null){
                intent= Intent(this,menuLateral::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
            }
        }
        //-----------------------------------------------------------------------------------
    }
    //con esta funcion inicio sesion
    private fun login(user:String,pass:String){

        auth.signInWithEmailAndPassword(user, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //si la autenticacion funciona, entonces se ejecuta el siguiente codigo
                    val user = auth.currentUser
                    intent= Intent(this,menuLateral::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                    startActivity(intent)
            finish()
                } else {
                  //Si la autenticacion falla, entonces mostrara el siguiente mensaje xD
                    Toast.makeText(baseContext, "Error en la autenticacion",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }





    }
//con esta funcion permite que la sesion no se cierre.
    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(door)
    }

    override fun onStop() {
        super.onStop()
        if (door != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(door)
        }
    }
    override fun onBackPressed() {
        super.finish()
    }
}
