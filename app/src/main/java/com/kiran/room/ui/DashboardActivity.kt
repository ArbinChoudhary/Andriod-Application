package com.kiran.room.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton
import com.kiran.room.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnAddRoom : AppCompatImageButton
    private lateinit var btnViewRoom : AppCompatImageButton
    private lateinit var btnGoogleMap : AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnAddRoom = findViewById(R.id.btnAddRoom)
        btnViewRoom = findViewById(R.id.btnViewRoom)
        btnGoogleMap = findViewById(R.id.btnGoogleMap)

        btnAddRoom.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,AddroomActivity::class.java))
        }

        btnViewRoom.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,ViewRoomActivity::class.java))
        }

        btnGoogleMap.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,MapsActivity::class.java))
        }
    }
}