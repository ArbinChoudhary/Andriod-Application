package com.kiran.room.repository

import com.kiran.room.api.UserAPI
import com.kiran.room.api.MyApiRequest
import com.kiran.room.api.ServiceBuilder
import com.kiran.room.model.User
import com.kiran.room.response.LoginResponse

class UserRepository : MyApiRequest(){

    private val myApi =
        ServiceBuilder.buildService(UserAPI::class.java)

    suspend fun checkUser(username : String,password :String) : LoginResponse{
        return apiRequest {
            myApi.checkUser(username,password)
        }
    }

    suspend fun registerUser(user : User) : LoginResponse{
        return apiRequest {
            myApi.registerUser(user)
        }
    }
}