package com.midterm2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm2.models.Response
import com.midterm2.rest.DataSource
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    private val _users= MutableLiveData<Response>()
    val users: LiveData<Response> = _users

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = DataSource().fetchUsers()
                _users.postValue(response)
            } catch (e: Exception) {
                Log.d("userApi", e.message.toString())
            }
        }
    }
}