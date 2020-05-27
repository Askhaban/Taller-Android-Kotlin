package com.example.taller.model

import java.text.SimpleDateFormat
import java.util.*

class ordenTrabajo(
    var placa:String="NN",
    var moto:String,
    var nombrecliente:String,
    var celular:String="",
    var detalle:String,
    var mo:String=""
)
fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun validar(placa:String):String{


return placa
}
