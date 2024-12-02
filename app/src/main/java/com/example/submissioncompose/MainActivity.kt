package com.example.submissioncompose

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.submissioncompose.model.Article
import com.example.submissioncompose.screen.detail.DetailScreen
import com.example.submissioncompose.screen.home.HomeScreen
import com.example.submissioncompose.screen.profile.ProfileScreen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionComposeTheme {
                SubmissionComposeApp()
            }
        }
    }
}

@Composable
fun SubmissionComposeApp(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            HomeScreen(navController = navController) { article ->
                navController.navigate(
                    "article?name=${Uri.encode(article.name)}&author=${Uri.encode(article.author)}" +
                            "&title=${Uri.encode(article.title)}&urlToImage=${Uri.encode(article.urlToImage)}" +
                            "&description=${Uri.encode(article.description)}&publishedAt=${Uri.encode(article.publishedAt)}"
                )
            }
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable(
            route = "article?name={name}&author={author}&title={title}&urlToImage={urlToImage}&description={description}&publishedAt={publishedAt}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("author") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("urlToImage") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("publishedAt") { type = NavType.StringType }
            )
        ){ backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val author = backStackEntry.arguments?.getString("author")
            val title = backStackEntry.arguments?.getString("title")
            val urlToImage = backStackEntry.arguments?.getString("urlToImage")
            val description = backStackEntry.arguments?.getString("description")
            val publishedAt = backStackEntry.arguments?.getString("publishedAt")

            val article = Article(
                name = name ?: "",
                author = author ?: "",
                title = title ?: "",
                urlToImage = urlToImage ?: "",
                description = description ?: "",
                publishedAt = publishedAt ?: ""
            )
            DetailScreen(navController = navController, article = article)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SubmissionComposeTheme {
        SubmissionComposeApp()
    }
}