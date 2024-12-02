package com.example.submissioncompose.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.submissioncompose.R
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Scaffold (
        topBar = { AppBarProfile(navController = navController) },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.my_foto),
                contentDescription = "foto profile",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = modifier.height(16.dp))
            Text("Muhammad Mizzy")
            Spacer(modifier = modifier.height(8.dp))
            Text("mizzy12342@gmail.com")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarProfile(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Column {
        TopAppBar(
            title = { Text("Title") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back icon",
                    )
                }
            }
        )
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SubmissionComposeTheme {
//        ProfileScreen()
    }
}