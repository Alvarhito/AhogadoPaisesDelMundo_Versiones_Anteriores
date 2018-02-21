package com.example.ceisutb01.ahogadopaisesdelmundo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.app.Activity
import android.util.Log
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    val words = arrayOf("C","O","L","O","M","B","I","A")
    val textView= arrayOf(textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBtn()
    }
    override fun onClick(v: View) {
        val b = v as Button
        v.visibility=View.INVISIBLE
        v.isEnabled=false
        Toast.makeText(this,b.getText().toString(),Toast.LENGTH_SHORT).show()
        /*for(i in 0..7){
            if(b.getText().toString()==words[i]){
                textView[i].setText(words[i])
            }
        }*/

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
