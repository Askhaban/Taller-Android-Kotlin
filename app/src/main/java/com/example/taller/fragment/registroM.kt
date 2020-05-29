package com.example.taller.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.taller.MainActivity

import com.example.taller.R
import com.example.taller.menuLateral
import com.example.taller.model.getCurrentDateTime
import com.example.taller.model.ordenTrabajo
import com.example.taller.model.toString
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registro_m.*

/**
 * A simple [Fragment] subclass.
 */
class registroM : Fragment() {
    private lateinit var database: DatabaseReference// ...
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance().reference

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_m, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnguardar.setOnClickListener(){
            crearOrdenTrabajo()


        }

        btncancelar.setOnClickListener(){
            try{
                val cerrar= Intent(activity, menuLateral::class.java)
                cerrar.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(cerrar)
                this.activity?.finish()
            }catch (e:Error){
                print("errror este ==>" + e)

            }
        }

    }

    fun crearOrdenTrabajo(){
        val placa= txtplaca.text.toString()
        var moto=txtmoto.text.toString()
        var nameClient=txtcliente.text.toString()
        var celular=txtcelular.text.toString()
        var detalle=txtdetalle.text.toString()
        var precio=txtvalormanoobra.text.toString()


        val fecha= getCurrentDateTime().toString("-yyyy-MM-dd HH:mm:ss")
        val fecha2=placa+fecha
        Log.d("vea su fecha:", fecha2)
        var Orden = ordenTrabajo(placa,moto,nameClient,celular,detalle,precio)
        database.child("ordenTrabajo").child(fecha2).setValue(Orden)
    }

}
