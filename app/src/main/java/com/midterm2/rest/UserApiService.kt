package com.midterm2.rest

import com.midterm2.models.Response
import retrofit2.http.GET

interface UserApiService {
    @GET("api/?inc=nat,name,email&results=10")
    suspend fun getUsers(): Response
}