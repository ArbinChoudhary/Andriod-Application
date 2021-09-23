package com.kiran.room.api

import com.kiran.room.model.Roomm
import com.kiran.room.response.AddRoomResponse
import com.kiran.room.response.DeleteRoomResponse
import com.kiran.room.response.ImageResponse
import com.kiran.room.response.RoomResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface RoomAPI {

    @POST("room/")
    suspend fun insertRoom(
        @Header("Authorization") token: String,
        @Body room: Roomm
    ): Response<AddRoomResponse>

    @GET("room/")
    suspend fun getAllRooms(
        @Header("Authorization") token: String
    ): Response<RoomResponse>

    @DELETE("room/{id}")
    suspend fun deleteRoom(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<DeleteRoomResponse>

    @Multipart
    @PUT("room/{id}/photo")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): Response<ImageResponse>
}