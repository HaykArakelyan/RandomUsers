package com.midterm2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.midterm2.models.User

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()

        viewModel.users.observe(this) { user ->
            when (user.results?.isNotEmpty()) {
                true -> setContent {
                    Column() {
                        Header()
                        UserList(users = viewModel.users.observeAsState().value?.results)
                    }
                }
                else -> {
                    setContent {
                        Text(
                            text = "Loading...s",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h5,
                            color = Color.Gray,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .height(150.dp)
            .background(Color.Red)
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Users",
            style = MaterialTheme.typography.h4,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun UserList(users: List<User>?) {

    Log.d("userApi", users.toString())

    when (users?.isNotEmpty()) {
        true -> {
            LazyColumn {
                items(users) { user ->
                    Card(
                        Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${user.name?.first} ${user.name?.last}",
                                    style = MaterialTheme.typography.h5,
                                )
                                Text(
                                    text = "${user.nat}",
                                    style = MaterialTheme.typography.h6,
                                    color = Color.Gray,
                                )
                            }

                            Row(
                                modifier = Modifier.padding(8.dp),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = "${user.email}",
                                    style = MaterialTheme.typography.h6,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
        else -> {
            Text(
                text = "Something went wrong",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
