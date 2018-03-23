package com.example.ceisutb01.ahogadopaisesdelmundo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    var nombre="No hay nada"
    val Country = Pais()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        playButton.setOnClickListener{
            selectPais()
            /*val boton_vs = Intent(this, MainActivity::class.java)
            boton_vs.putExtra("Pais", nombre)
            startActivity(boton_vs)*/
        }

        /*creditosButton.setOnClickListener{
            val boton_vs = Intent(this, MainActivity::class.java)
            startActivity(boton_vs)
        }*/
    }
    fun selectPais() {
        //val ran = (Math.random() * 500).toInt()
        //val url="https://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=Paises%20Bajos%20del%20Sur"
        //val url="https://restcountries.eu/rest/v2/all"
        //val url = "https://restcountries.eu/rest/v2/all?fields=translations"
        val url="https://restcountries.eu/rest/v2/all?fields=translations;capital;region;flag"
        //val url = "https://pokeapi.co/api/v2/pokemon/" + "15"
        getJson(url)

        /*val jsonobj=JSONObject()
        val que= Volley.newRequestQueue(this@MainActivity)
        val req =JsonObjectRequest(Request.Method.GET,url,jsonobj,Response.Listener {response ->
            Toast.makeText(this," Exito...", Toast.LENGTH_LONG).show()

        },Response.ErrorListener {
            Toast.makeText(this," Error",Toast.LENGTH_LONG).show()
        })
        que.add(req)
        var prueba= Prueba as TextView
        prueba.text="Entró a la funcion 'selectPais()'"*/
    }

    fun getJson(url: String) {
        Log.d("depuracion","getJson")

        //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
        url.httpGet().responseJson{ request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    //Toast.makeText(this," Error de Conexion", Toast.LENGTH_LONG).show()

                    val boton_vs = Intent(this, MainActivity::class.java)
                    boton_vs.putExtra("Pais", nombre)
                    boton_vs.putExtra("Reiniciar", "false")
                    startActivity(boton_vs)
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.d("data", data.toString())
                    //var prueba= Prueba as TextView

                    Country.Organizar(data)
                    nombre=Country.getName()
                    //

                    //Toast.makeText(this,nombre.toString(), Toast.LENGTH_LONG).show()
                    //val boton_vn = Intent(this, MainActivity::class.java)
                    //startActivity(boton_vn)
                    val boton = Intent(this, MainActivity::class.java)
                    boton.putExtra("Pais", nombre)
                    boton.putExtra("Reiniciar", false)
                    boton.putExtra("Lista1",Country.nivel1)
                    //boton.putExtra("Lista2",Country.nivel2)
                    //boton.putExtra("Lista3",Country.nivel3)
                   // boton.putExtra("Lista4",Country.nivel4)

                    startActivity(boton)
                }
            }
        }

    }
}
