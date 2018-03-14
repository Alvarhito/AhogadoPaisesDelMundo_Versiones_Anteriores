package com.example.ceisutb01.ahogadopaisesdelmundo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        playButton.setOnClickListener{
            val boton_vs = Intent(this, MainActivity::class.java)
            startActivity(boton_vs)
        }

        /*creditosButton.setOnClickListener{
            val boton_vs = Intent(this, MainActivity::class.java)
            startActivity(boton_vs)
        }*/
    }
}
