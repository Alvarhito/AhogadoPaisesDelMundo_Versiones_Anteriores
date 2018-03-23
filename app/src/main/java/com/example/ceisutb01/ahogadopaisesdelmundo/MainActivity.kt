package com.example.ceisutb01.ahogadopaisesdelmundo

import android.os.Bundle
import android.view.View
import java.util.Random
import android.app.Activity
import android.content.Intent
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

import android.widget.Button
import android.widget.TextView

class MainActivity : Activity(), View.OnClickListener {

    val letter= arrayOf(("CHAD"),("OMÁN"),("PERÚ"),("NAURU"),("TOGO"),("TONGO"),("TÚNEZ"),("SUIZA"),("SIRIA"),("SAMOA"),("NÍGER"),("NEPAL"),("BENÍN"),("KENIA"),("BUTÁN"),
                        ("ANGOLA"),("ANDORRA"),("ARMENIA"),("AUSTRIA"),("BAHAMAS"),("BARÉIN"),("BÉLGICA"),("CAMERÚN"),("CANADÁ"),("ECUADOR"),("ESPAÑA"),("GAMBIA"),("KOSOVO"),("KUWAIT"),("POLONIA"),
                         ("VENEZUELA"),("VATICANO"),("TANZANIA"),("TAILANDIA"),("SUDÁFRICA"),("SINGAPUR"),("PORTUGAL"),("PALESTINA"),("PAKISTÁN"),("NICARAGUA"),("NAMIBIA"),("MONGOLIA"),("MICRONESIA"),("MARRUECOS"),("MALDIVAS"),
                         ("LIECHTENSTEIN"),("LUXEMBURGO"),("MADAGASCAR"),("MOZAMBIQUE"),("SEYCHELLES"),("TAYIKISTÁN"),("UZBEKISTÁN"),("AFGANISTÁN"),("ARGENTINA"),("BAMGLADESH"),("BIELORRUSIA"),("DINAMARCA"),("ESLOVAQUIA"),("FINLANDIA"),("KIRGUISTÁN"))

    var inicial=0
    var final=14

    var secion=1

    val random = Random()
    var num = random.nextInt(final - inicial)
    var textviews = ArrayList<TextView>()
    var termino = 0
    var NumVidas = 7
    var Tpuntos = 0
    var auxPuntos = 5
    var Palabra=letter[num]

    var nivel1 = ArrayList<nation>()
    var nivel2 = ArrayList<nation>()
    var nivel3 = ArrayList<nation>()
    var nivel4 = ArrayList<nation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nombre =intent.extras.getString("Pais")

        var prueba= Prueba as TextView

        var reiniciar=intent.extras.getString("Reiniciar")

        if(reiniciar=="true"){
            inicial=intent.extras.getInt("Init")
            final=intent.extras.getInt("End")
            Tpuntos=intent.extras.getInt("Score")
            secion=intent.extras.getInt("Secion")

            var puntitos = Puntos as TextView
            puntitos.text = "Puntos: " + Tpuntos.toString()
            prueba.text=Tpuntos.toString()


            num = (random.nextInt(final - inicial))+inicial
            nombre=letter[num]
        }else{
            nivel1= intent.extras!!.getSerializable("Lista1") as ArrayList<nation>
            nivel2= intent.extras!!.getSerializable("Lista2") as ArrayList<nation>
            nivel3= intent.extras!!.getSerializable("Lista3") as ArrayList<nation>
            nivel4= intent.extras!!.getSerializable("Lista4") as ArrayList<nation>
        }

        if(nombre=="No hay nada"){
            //prueba.text=Palabra
            init(Palabra.length)
        }else{
            nombre=nombre.toUpperCase()
            Palabra=nombre
            init(Palabra.length)
            //prueba.text=nombre

        }
        setBtn()

        Menu2.setOnClickListener {
            val boton_vs = Intent(this, Main2Activity::class.java)
            startActivity(boton_vs)
            finish()
        }
        Menu.setOnClickListener {
            val boton_vs = Intent(this, Main2Activity::class.java)
            startActivity(boton_vs)
            finish()
        }
        Siguente.setOnClickListener {

            val boton_vs = Intent(this, MainActivity::class.java)
            if(secion==2){
                inicial=final+1
                final=inicial+14
                secion=1
            }else{
                secion=2
            }
            boton_vs.putExtra("Pais", nombre)
            boton_vs.putExtra("Reiniciar", "true")
            boton_vs.putExtra("Init", inicial)
            boton_vs.putExtra("End", final)
            boton_vs.putExtra("Score", Tpuntos)
            boton_vs.putExtra("Secion", secion)
            startActivity(boton_vs)
            finish()
        }
        Reiniciar.setOnClickListener {
            finish()
            //m.selectPais()
        }
        Ayuda.setOnClickListener {
            Ayuda.isEnabled = false
            ayudando()
            verificaGano()
        }
    }

    fun ayudando() {
        val ra = Random()
        var VRam = ra.nextInt((Palabra.length - 1) - 0)
        //var VRam=numL[num]-1

        var aux = true
        while (aux) {
            aux = false
            for (i in 0..Palabra.length - 1) {
                //Log.d("TextView",textviews[i].text.toString())
                if (textviews[i].getText().toString() == Palabra[VRam] + " ") {
                    aux = true
                    VRam = ra.nextInt((Palabra.length - 1) - 0)
                    break;
                }

            }
        }
        textviews[VRam].text = Palabra[VRam] + " "
        termino += 1
    }

    fun init(number: Int) {

        val For_Letter = ForLetter as LinearLayout
        for (i in 0..number - 1) {
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
        var conta=0

       // var prueba= Prueba as TextView
        //prueba.text=""

        for (i in Palabra) {

            //prueba.text =prueba.getText().toString() + i.toString()+" "+b.getText().toString()

            if (b.getText().toString() == i.toString() || setToTilde(b.getText().toString())==i.toString()) {
                if (textviews[conta].getText().toString() != b.getText().toString() + " ") {
                    textviews[conta].text = i + " "
                    entro = true
                    termino += 1
                }
            }
            conta+=1
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
            Buttonoff(false)
        }
    }

    fun verificaGano() {
        if (termino == Palabra.length) {
            val winL = youwintext as TextView
            val venI = VenI as ImageView

            venI.visibility = View.VISIBLE
            winL.visibility = View.VISIBLE

            var prueba= Prueba as TextView
            prueba.text=(final+1).toString()

            auxPuntos = ((final+1)/15)*(50)
            Tpuntos=Tpuntos+auxPuntos
            var puntitos = Puntos as TextView
            puntitos.text = "Puntos: " + Tpuntos.toString()

            Buttonoff(true)
        }
    }

    fun Buttonoff(gano: Boolean) {
        val menu = Menu as Button
        val ayuda = Ayuda as Button
        val For_Letterabc = Vocales as LinearLayout
        val For_Letterabc2 = LetrasABC as LinearLayout

        val menu2 = Menu2 as Button
        val sigieunte = Siguente as Button
        val reiniciar = Reiniciar as Button

        menu.visibility = View.INVISIBLE
        ayuda.visibility = View.INVISIBLE

        menu2.visibility = View.VISIBLE
        if(gano) {
            sigieunte.visibility = View.VISIBLE
        }else{
            reiniciar.visibility=View.VISIBLE
        }

        For_Letterabc.visibility = View.INVISIBLE
        For_Letterabc2.visibility = View.INVISIBLE
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

    fun setToTilde(l:String):String{
        val abecedario: HashMap<String, String> = hashMapOf("A" to "Á", "B" to "B",
                "C" to "C", "D" to "D", "E" to "É", "F" to "F", "G" to "G", "H" to "H",
                "I" to "Í", "J" to "J", "K" to "K", "L" to "L", "M" to "M", "N" to "N",
                "Ñ" to "Ñ", "O" to "Ó", "P" to "P", "Q" to "Q", "R" to "R","S" to "S",
                "T" to "T", "U" to "Ú", "V" to "V", "W" to "W", "X" to "X","Y" to "Y",
                "Z" to "Z")
        return abecedario[l].toString()

    }
}
