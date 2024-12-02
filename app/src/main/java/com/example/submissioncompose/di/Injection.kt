package com.example.submissioncompose.di

import com.example.submissioncompose.data.ArticleRepository

object Injection {
    fun provideRepository(): ArticleRepository {
        return ArticleRepository.getInstance()
    }
}