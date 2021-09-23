package com.kiran.room.dao

import androidx.room.*
import com.kiran.room.model.Roomm

@Dao
interface RoomDAO {
    @Insert
    suspend fun insertRoom(room : Roomm)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBulkRoom(room: List<Roomm>)

    @Query("SELECT * FROM Roomm")
    suspend fun getAllRooms() : MutableList<Roomm>

    @Update
    suspend fun updateRoom(room : Roomm)

    @Delete
    suspend fun DeleteRoom(room : Roomm)

    @Query("DELETE FROM Roomm")
    suspend fun DeleteAllRooms()
}