package com.kiran.room.ui

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiran.room.R
import com.kiran.room.adapter.RoomAdapter
import com.kiran.room.notification.NotificationChannels
import com.kiran.room.repository.RoomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ViewRoomActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_room)
        recyclerView = findViewById(R.id.recyclerView)

        loadRooms()
    }

    private fun loadRooms() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val roomRepository = RoomRepository()
                val response = roomRepository.getAllRooms()
                if (response.success == true) {
                    // Insert all the rooms in room database
                    roomRepository.insertBulkRoom(this@ViewRoomActivity, response.data!!)

                    // get data from room database
                    val lstRooms =
                        roomRepository.getAllRoomsFromRoom(this@ViewRoomActivity)

                    showNotification(lstRooms.size)
                    withContext(Main) {
                        recyclerView.adapter =
                            RoomAdapter(this@ViewRoomActivity, lstRooms!!)
                        recyclerView.layoutManager = LinearLayoutManager(this@ViewRoomActivity)
                    }
                }
            } catch (ex: Exception) {
                withContext(Main) {
                    Toast.makeText(
                        this@ViewRoomActivity,
                        "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showNotification(size: Int) {
        val notificationManager = NotificationManagerCompat.from(this)
        val activityIntent = Intent(this, MapsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("My notification")
            .setContentText("Total number of rooms are $size")
            .setColor(Color.BLUE)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }

}
