package com.example.repository

import com.example.model.User

interface UserRepository {

    suspend fun getUserList(): List<User>

    suspend fun getUserDetail(loginId: String): User
}
