package com.example.submissioncompose.data

import com.example.submissioncompose.model.Article
import com.example.submissioncompose.utils.DummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ArticleRepository {
    private val dataArticle = mutableListOf<Article>()

    init {
        DummyData.articles.forEach { article ->
            val source = article["source"] as? Map<*, *>
            val name = source?.get("name") as? String ?: ""
            val title = article["title"] as? String ?: ""
            val author = article["author"] as? String ?: ""
            val description = article["description"] as? String ?: ""
            val urlToImage = article["urlToImage"] as? String ?: ""
            val publishedAt = article["publishedAt"] as? String ?: ""
            dataArticle.add(Article(
                title = title,
                name = name,
                author = author,
                description = description,
                urlToImage = urlToImage,
                publishedAt = publishedAt
            ))
        }
    }

    fun getAllArticle(): Flow<List<Article>> {
        return flowOf(dataArticle)
    }

    companion object {
        @Volatile
        private var instance: ArticleRepository? = null

        fun getInstance(): ArticleRepository =
            instance ?: synchronized(this) {
                ArticleRepository().apply {
                    instance = this
                }
            }
    }
}