package com.example.submissioncompose.screen.detail

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.submissioncompose.R
import com.example.submissioncompose.model.Article
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme
import com.example.submissioncompose.utils.timeAgo

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    article: Article
){
    Scaffold(
        topBar = { AppBarDetail(navController = navController) }
    ) { innerPadding ->
        DetailContent(innerPadding = innerPadding, article = article)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarDetail(
    modifier: Modifier = Modifier,
    navController: NavController
){
    Column {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Detail Article")
                }

            },
            navigationIcon = {
                Box(
                    modifier = modifier
                        .width(50.dp)
                        .height(50.dp)
                        .border(1.dp, color = Color.Black)
                        .clickable {
                            navController.navigateUp()
                        },
                    contentAlignment = Alignment.Center
                    ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back icon",
                    )
                }
            },
            actions = {
                Spacer(
                    modifier = modifier.width(50.dp)
                )
            }
        )
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    article: Article
){
    Column(
        modifier = modifier
            .padding(innerPadding)
            .padding(top = 25.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = article.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 40.sp
        )
        Spacer(modifier.height(20.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = article.author
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Text(
                    text = timeAgo(article.publishedAt)
                )
            } else {
                Text(
                    text = ""
                )
            }
        }
        Spacer(modifier.height(16.dp))
        ImageFromUrlWithGlide(url = article.urlToImage)
        Spacer(modifier.height(25.dp))
        Text(article.description)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageFromUrlWithGlide(
    modifier: Modifier = Modifier,
    url: String,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10))
            .background(Color.Gray)
    ) {
        GlideImage(
            model = url,
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            loading = placeholder(R.drawable.placeholder_image_2),
            failure = placeholder(R.drawable.placeholder_image_2)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailScreenPreview(){
//    SubmissionComposeTheme {
//        DetailScreen()
//    }
//}