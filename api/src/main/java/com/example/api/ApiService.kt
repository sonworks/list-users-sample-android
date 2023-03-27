package com.example.api

import com.example.api.response.UserDetailResponse
import com.example.api.response.UserListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getUserList(): Single<List<UserListResponse>>

    @GET("users/{user_name}")
    fun getUserDetail(@Path("user_name") userName: String): Single<UserDetailResponse>
}
