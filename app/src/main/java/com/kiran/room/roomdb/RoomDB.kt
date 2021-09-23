package com.kiran.room.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kiran.room.dao.RoomDAO
import com.kiran.room.dao.UserDAO
import com.kiran.room.model.Roomm
import com.kiran.room.model.User


@Database(
    entities = [(Roomm::class), (User::class)],
    version = 2,
    exportSchema = false
)
abstract class RoomDB : RoomDatabase() {
    abstract fun getRoomDAO(): RoomDAO
    abstract fun getUserDAO(): UserDAO

    companion object {
        @Volatile
        private var instance: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            if (instance == null) {
                synchronized(RoomDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java,
                "RoomDB"
            ).build()
    }
}