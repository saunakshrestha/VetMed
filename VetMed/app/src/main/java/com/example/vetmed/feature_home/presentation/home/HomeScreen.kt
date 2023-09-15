package com.example.vetmed.feature_home.presentation.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vetmed.R
import com.example.vetmed.feature_animal.data.repository.Users
import com.example.vetmed.feature_animal.presentation.components.EmptyPage
import com.example.vetmed.feature_animal.util.RequestState
import com.example.vetmed.feature_authentication.data.User
import com.example.vetmed.feature_home.presentation.home.components.VetContent

private const val TAG = "HomeScreen"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCallButtonClick: (String) -> Unit,
    vets: Users
) {
    Scaffold(
        topBar = { VetTopBar() },
        content = {
            when (vets) {
                is RequestState.Success -> {
                    VetContent(
                        paddingValues = it,
                        vets = vets.data,
                        onCallButtonClick = onCallButtonClick
                    )
                }

                is RequestState.Error -> {
                    EmptyPage(
                        title = "Error",
                        subtitle = "${vets.error.message}"
                    )
                }

                is RequestState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    EmptyPage(title = "No Vets Available", subtitle = "Try Logging In Later")

                }
            }


        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VetTopBar(
) {

    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_veterinarian_50),
                    contentDescription = "Logo",
                    tint = Color(0xFF26A69A)
                )
                Spacer(modifier = Modifier.width(5.dp))

                Text("Vets")
            }
        },
    )
}

@Composable
fun EmptyUserPage(
    title: String = "No Vets Found",
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Medium
            )
        )
    }

}

val userList = mutableListOf(
    User().apply {
        userName = "Alice"
        email = "alice@example.com"
        profile = ""
        description = "I'm a pet lover and a veterinarian."
        isVet = true
        isAvailable = true
    },
    User().apply {
        userName = "Bob"
        email = "bob@example.com"
        profile = ""
        description = "I'm a pet owner and a dog trainer."
        isVet = false
        isAvailable = true
    },
    User().apply {
        userName = "Cindy"
        email = "cindy@example.com"
        profile = ""
        description = "I'm a pet owner and a cat lover."
        isVet = false
        isAvailable = false
    },
    User().apply {
        userName = "Dan"
        email = "dan@example.com"
        profile = ""
        description = "I'm a pet owner and a bird watcher."
        isVet = false
        isAvailable = true
    }
)


