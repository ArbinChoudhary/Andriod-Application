package com.kiran.room.response

import com.kiran.room.model.Roomm
data class AddRoomResponse(
    val success : Boolean? = null,
    val data : Roomm? = null,
)