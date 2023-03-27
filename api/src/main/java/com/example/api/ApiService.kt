package com.example.api

import com.example.api.response.UserDetailResponse
import com.example.api.response.UserListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getUserList(
        @Header("Authorization") authorization: String?
    ): Single<List<UserListResponse>>

    @GET("users/{user_name}")
    fun getUserDetail(
        @Header("Authorization") authorization: String?,
        @Path("user_name") userName: String
    ): Single<UserDetailResponse>
}
