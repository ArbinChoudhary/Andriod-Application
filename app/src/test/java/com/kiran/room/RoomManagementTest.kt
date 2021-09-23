package com.kiran.room

import com.kiran.room.api.ServiceBuilder
import com.kiran.room.model.Roomm
import com.kiran.room.model.User
import com.kiran.room.repository.RoomRepository
import com.kiran.room.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RoomManagementTest {

    private lateinit var userRepository: UserRepository
    private lateinit var roomRepository: RoomRepository

    // -----------------------------User Testing-----------------------------
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.checkUser("arbin", "arbin123")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun registerUser() = runBlocking {
        val user =
            User(fname = "test", lname = "test",
                username = "zxxczsasdxcx", password = "testpassword")
        userRepository = UserRepository()
        val response = userRepository.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    // -----------------------------Student Testing-----------------------------
    @Test
    fun addRoom() = runBlocking {
        userRepository = UserRepository()
        roomRepository = RoomRepository()

        val room =
            Roomm(roomnum = "roomNum", roomtype = 3)

        ServiceBuilder.token ="Bearer " + userRepository.checkUser("arbin","arbin123").token
        val expectedResult = true
        val actualResult = roomRepository.insertRoom(room).success
        Assert.assertEquals(expectedResult, actualResult)
    }
}