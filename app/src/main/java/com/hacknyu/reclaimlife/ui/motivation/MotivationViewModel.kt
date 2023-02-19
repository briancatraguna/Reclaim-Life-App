package com.hacknyu.reclaimlife.ui.motivation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.model.Quotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MotivationViewModel(
    private val repository: Repository
): ViewModel() {

    private val _motivationViewState: MutableStateFlow<MotivationViewState> = MutableStateFlow(MotivationViewState())
    val motivationViewState: StateFlow<MotivationViewState> get() = _motivationViewState

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            repository.getQuotes().collect { quotes ->
                _motivationViewState.value = _motivationViewState.value.copy(
                    quotes = quotes
                )
            }
        }
    }
}

data class MotivationViewState(
    var quotes: List<Quotes> = listOf()
)