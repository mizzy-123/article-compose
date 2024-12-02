package com.example.submissioncompose.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.submissioncompose.di.Injection
import com.example.submissioncompose.model.Article
import com.example.submissioncompose.ui.ViewModelFactory
import com.example.submissioncompose.ui.common.UiState
import com.example.submissioncompose.ui.components.MenuColumn
import com.example.submissioncompose.utils.DummyData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (articleDetail: Article) -> Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllArticle()
            }
            is UiState.Success -> {
                HomeContent(
                    navController = navController,
                    articles = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    articles: List<Article>,
    navigateToDetail: (articleDetail: Article) -> Unit
){
    Scaffold(
        topBar = { AppBarHome(navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MenuColumn(modifier = modifier
            .padding(innerPadding),
            articles = articles,
            navigateToDetail = navigateToDetail
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome(
    modifier: Modifier = Modifier,
    navController: NavController,
){
    Column {
        TopAppBar(
            title = { Text("Article") },
            actions = {
                IconButton(onClick = {
                    navController.navigate("profile")
                }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Account",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        )
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    }
}