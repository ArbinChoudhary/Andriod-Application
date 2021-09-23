package com.kiran.room.response

import com.kiran.room.model.Roomm

data class RoomResponse(
    val success: Boolean? = null,
    val data: MutableList<Roomm>? =null
)