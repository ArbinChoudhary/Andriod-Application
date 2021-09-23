package com.kiran.room.repository

import android.content.Context
import com.kiran.room.api.MyApiRequest
import com.kiran.room.api.ServiceBuilder
import com.kiran.room.api.RoomAPI
import com.kiran.room.model.Roomm
import com.kiran.room.response.AddRoomResponse
import com.kiran.room.response.DeleteRoomResponse
import com.kiran.room.response.ImageResponse
import com.kiran.room.response.RoomResponse
import com.kiran.room.roomdb.RoomDB
import okhttp3.MultipartBody

class RoomRepository : MyApiRequest() {

    private val roomAPI =
        ServiceBuilder.buildService(RoomAPI::class.java)

    suspend fun insertRoom(room: Roomm): AddRoomResponse {
        return apiRequest {
            roomAPI.insertRoom(ServiceBuilder.token!!, room)
        }
    }

    suspend fun getAllRooms(): RoomResponse {
        return apiRequest {
            roomAPI.getAllRooms(ServiceBuilder.token!!)
        }
    }

    suspend fun insertBulkRoom(context : Context, rooms : List<Roomm>){
        // Delete all rooms
        RoomDB.getInstance(context).getRoomDAO().DeleteAllRooms()
        // Insert all data in database
        RoomDB.getInstance(context).getRoomDAO().insertBulkRoom(rooms )
    }

    // get data from repository
    suspend fun getAllRoomsFromRoom(context : Context) : MutableList<Roomm>{
        return RoomDB.getInstance(context).getRoomDAO().getAllRooms()
    }

    suspend fun deleteRooms(id: String): DeleteRoomResponse {
        return apiRequest {
            roomAPI.deleteRoom(ServiceBuilder.token!!, id)
        }
    }

    suspend fun uploadImage(id: String, body: MultipartBody.Part)
    : ImageResponse {
        return apiRequest {
            roomAPI.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }


}
