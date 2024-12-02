package com.example.submissioncompose.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.ArticleRepository
import com.example.submissioncompose.model.Article
import com.example.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ArticleRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Article>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>>
        get() = _uiState

    fun getAllArticle(){
        viewModelScope.launch {
            repository.getAllArticle()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { article ->
                    _uiState.value = UiState.Success(article)
                }
        }
    }
}