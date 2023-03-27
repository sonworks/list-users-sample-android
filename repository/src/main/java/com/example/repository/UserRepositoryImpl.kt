package com.example.repository

import com.example.api.ApiService
import com.example.model.User
import kotlinx.coroutines.rx2.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {

    override suspend fun getUserList(): List<User> {
        val response = apiService.getUserList().await()
        return response.map {
            User(
                login = it.login,
                avatarUrl = it.avatarUrl
            )
        }
    }

    override suspend fun getUserDetail(loginId: String): User {
        val response = apiService.getUserDetail(loginId).await()
        return User(
            login = response.login,
            avatarUrl = response.avatarUrl,
            type = response.type,
            siteAdmin = response.siteAdmin,
            name = response.name,
            company = response.company,
            blog = response.blog,
            location = response.location,
            createdAt = response.createdAt
        )
    }
}
