package com.example.submissioncompose.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.submissioncompose.model.Article

@Composable
fun MenuColumn(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    navigateToDetail: (articleDetail: Article) -> Unit
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(articles){ article ->
            ItemMenuColumn(article = article, navigateToDetail = navigateToDetail)
        }
    }
}