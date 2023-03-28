package com.midterm2.rest

import com.midterm2.models.Response

class DataSource {
    suspend fun fetchUsers(): Response{
        return RetrofitHelper.getInstance().create(UserApiService::class.java).getUsers()
    }
}