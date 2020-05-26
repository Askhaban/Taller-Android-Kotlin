package com.example.taller.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.taller.MainActivity
import com.example.taller.R

import com.example.taller.registroMotos
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.Console

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnExit.setOnClickListener(){

            CerrarSesion()



        }

        btnAdd.setOnClickListener(){

            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.registrom) }
        }

    }


    fun CerrarSesion(){
        FirebaseAuth.getInstance().signOut()
        try{
    val cerrar= Intent(activity,MainActivity::class.java)
             cerrar.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
  startActivity(cerrar)
            this.activity?.finish()
        }catch (e:Error){
            print("errror este ==>" + e)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}
