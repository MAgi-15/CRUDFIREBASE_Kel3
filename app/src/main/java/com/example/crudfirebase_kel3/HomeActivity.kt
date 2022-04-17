package com.example.crudfirebase_kel3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intentButton1: Button = findViewById(R.id.btncreatehome)
        intentButton1.setOnClickListener { viewCreate() }

        val intentButton2: Button = findViewById(R.id.btnreadhome)
        intentButton2.setOnClickListener { viewRead() }

    }

    private fun viewCreate() {
        val intent = Intent ( this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun viewRead() {
        val intent = Intent ( this, show::class.java)
        startActivity(intent)
    }
}