package com.example.ceisutb01.ahogadopaisesdelmundo

import android.os.Bundle
import android.view.View
import java.util.Random
import android.app.Activity
import android.util.Log
import android.content.Intent
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

import android.widget.Button
import android.widget.TextView
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class MainActivity : Activity(), View.OnClickListener {

    private val Country = Pais()

    val letter = arrayOf(arrayOf("C", "O", "L", "O", "M", "B", "I", "A"), arrayOf("V", "E", "N", "E", "Z", "U", "E", "L", "A"), arrayOf("A", "F", "G", "A", "N", "I", "S", "T", "A", "N"), arrayOf("A", "N", "G", "O", "L", "A"), arrayOf("A", "L", "B", "A", "N", "I", "A"), arrayOf("B", "A", "N", "G", "L", "A", "D", "E", "S", "H"), arrayOf("B", "U", "L", "G", "A", "R", "I", "A"), arrayOf("C", "A", "M", "E", "R", "U", "N"), arrayOf("C", "H", "I", "P", "R", "E"), arrayOf("E", "T", "I", "O", "P", "I", "A"), arrayOf("F", "I", "L", "I", "P", "I", "N", "A", "S"), arrayOf("G", "U", "A", "T", "E", "M", "A", "L", "A"), arrayOf("J", "O", "R", "D", "A", "N", "I", "A"), arrayOf("K", "E", "N", "Y", "A"), arrayOf("K", "U", "W", "A", "I", "T"), arrayOf("L", "I", "B", "E", "R", "I", "A"), arrayOf("L", "U", "X", "E", "M", "B", "U", "R", "G", "O"), arrayOf("M", "A", "U", "R", "I", "C", "I", "O"), arrayOf("P", "A", "K", "I", "S", "T", "A", "N"), arrayOf("P", "A", "R", "A", "G", "U", "A", "Y"), arrayOf("R", "U", "M", "A", "N", "I", "A"), arrayOf("S", "I", "N", "G", "A", "P", "U", "R"), arrayOf("S", "U", "R", "I", "N", "A", "M"), arrayOf("Y", "U", "G", "O", "S", "L", "A", "V", "I", "A"), arrayOf("M", "O", "Z", "A", "M", "B", "I", "Q", "U", "E"), arrayOf("O", "M", "A", "N"))
    val numL = arrayOf(8, 9, 10, 6, 7, 10, 8, 7, 6, 7, 9, 9, 8, 5, 6, 7, 10, 8, 8, 8, 7, 8, 7, 10, 10, 4)
    /*val words= arrayOf(("COLOMBIA")                     ,("VENEZUELA")                        ,("AFGANISTAN")                           ,("ANGOLA")               ,("ALBANIA")                  ,("BANGLADESH")                           ,("BULGARIA")                     ,("CAMERUN")                  ,("CHIPRE")               ,("ETIOPIA")                  ,("FILIPINAS")                        ,("GUATEMALA")                        ,("JORDANIA")                     ,("KENIA")            ,("KUWAIT")               ,("LIBERIA")                  ,("LUXEMBURGO")                           ,("MAURICIO")                     ,("PAKISTAN")                     ,("PARAGUAY")                     ,("RUMANIA")                  ,("SINGAPUR")                     ,("SURINAM")                  ,("YUGOSLAVIA")                           ,("MOZAMBIQUE")                           ,("OMAN")         )*/

    val random = Random()
    val num = random.nextInt(25 - 0)
    var textviews = ArrayList<TextView>()
    var termino = 0
    var NumVidas = 7
    var Tpuntos = 0
    var auxPuntos = 5
    var nombre = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("debug", "Inicio")
        setBtn()
        selectPais()
        init()

        Menu2.setOnClickListener {
            val boton_vs = Intent(this, Main2Activity::class.java)
            startActivity(boton_vs)
            super.onDestroy()
        }
        Menu.setOnClickListener {
            val boton_vs = Intent(this, Main2Activity::class.java)
            startActivity(boton_vs)
            super.onDestroy()
        }
        Reiniciar.setOnClickListener {
            val boton_vs = Intent(this, MainActivity::class.java)
            startActivity(boton_vs)
            super.onDestroy()
        }
        Ayuda.setOnClickListener {
            Ayuda.isEnabled = false
            ayudando()
            verificaGano()
        }
    }

    fun selectPais() {
        //val ran = (Math.random() * 500).toInt()
        //val url="https://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=Paises%20Bajos%20del%20Sur"
        //val url="https://restcountries.eu/rest/v2/all"
        val url = "https://restcountries.eu/rest/v2/all?fields=translations"
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
        url.httpGet().responseJson { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.d("data", data.toString())
                    nombre=Country.getName(data)
                }
            }
        }

    }


    fun ayudando() {
        print("hola mundo")
        val ra = Random()
        var VRam = ra.nextInt((numL[num] - 1) - 0)
        //var VRam=numL[num]-1

        var aux = true
        while (aux) {
            aux = false
            for (i in 0..numL[num] - 1) {
                //Log.d("TextView",textviews[i].text.toString())
                if (textviews[i].getText().toString() == letter[num][VRam] + " ") {
                    aux = true
                    VRam = ra.nextInt((numL[num] - 1) - 0)
                    break;
                }

            }
        }
        textviews[VRam].text = letter[num][VRam] + " "
        termino += 1
    }

    fun init() {

        val For_Letter = ForLetter as LinearLayout
        for (i in 0..numL[num] - 1) {
            val tv_dynamic = TextView(this)
            tv_dynamic.textSize = 30f
            tv_dynamic.text = "__ "
            For_Letter.addView(tv_dynamic)
            textviews.add(tv_dynamic)
        }
        //textviews[0].text=nombre

    }

    override fun onClick(v: View) {
        val b = v as Button
        v.isEnabled = false
        var entro = false
        for (i in 0..numL[num] - 1) {
            if (b.getText().toString() == letter[num][i]) {
                if (textviews[i].getText().toString() != b.getText().toString() + " ") {
                    textviews[i].text = b.getText().toString() + " "
                    entro = true
                    termino += 1
                }
            }
        }
        if (entro == false) {
            verificaPerdio()
            auxPuntos = 5;
        } else {
            var puntitos = Puntos as TextView
            Tpuntos += auxPuntos
            puntitos.text = "Puntos: " + Tpuntos.toString()
            auxPuntos += 5
        }
        verificaGano()
    }

    fun verificaPerdio() {
        var vidas = Vidas as TextView
        NumVidas = NumVidas - 1
        vidas.text = "Vidas: " + NumVidas.toString()
        if (NumVidas <= 0) {
            val loseL = youlosetext as TextView
            val venI = VenI as ImageView
            venI.visibility = View.VISIBLE
            loseL.visibility = View.VISIBLE
            Buttonoff()
        }
    }

    fun verificaGano() {
        if (termino == numL[num]) {
            val winL = youwintext as TextView
            val venI = VenI as ImageView
            venI.visibility = View.VISIBLE
            winL.visibility = View.VISIBLE
            Buttonoff()
        }
    }

    fun Buttonoff() {
        val menu = Menu as Button
        val ayuda = Ayuda as Button
        val For_Letterabc = LetrasABC as LinearLayout

        val menu2 = Menu2 as Button
        val reiniciar = Reiniciar as Button

        menu.visibility = View.INVISIBLE
        ayuda.visibility = View.INVISIBLE

        menu2.visibility = View.VISIBLE
        reiniciar.visibility = View.VISIBLE

        For_Letterabc.visibility = View.INVISIBLE
    }

    fun setBtn() {
        buttonA.setOnClickListener(this)
        buttonB.setOnClickListener(this)
        buttonC.setOnClickListener(this)
        buttonD.setOnClickListener(this)
        buttonE.setOnClickListener(this)
        buttonF.setOnClickListener(this)
        buttonG.setOnClickListener(this)
        buttonH.setOnClickListener(this)
        buttonI.setOnClickListener(this)
        buttonJ.setOnClickListener(this)
        buttonK.setOnClickListener(this)
        buttonL.setOnClickListener(this)
        buttonM.setOnClickListener(this)
        buttonN.setOnClickListener(this)
        buttonO.setOnClickListener(this)
        buttonP.setOnClickListener(this)
        buttonQ.setOnClickListener(this)
        buttonR.setOnClickListener(this)
        buttonS.setOnClickListener(this)
        buttonT.setOnClickListener(this)
        buttonU.setOnClickListener(this)
        buttonV.setOnClickListener(this)
        buttonW.setOnClickListener(this)
        buttonX.setOnClickListener(this)
        buttonY.setOnClickListener(this)
        buttonZ.setOnClickListener(this)
    }
}
