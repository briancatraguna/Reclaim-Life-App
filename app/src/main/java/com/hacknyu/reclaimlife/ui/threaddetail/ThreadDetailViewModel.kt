package com.hacknyu.reclaimlife.ui.threaddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.model.NewComment
import kotlinx.coroutines.flow.MutableStateFlow
import com.hacknyu.reclaimlife.model.Threads
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ThreadDetailViewModel(
    private val repository: Repository,
    threadId: String
): ViewModel() {

    private val _thread: MutableStateFlow<ThreadDetailViewState> = MutableStateFlow(
        ThreadDetailViewState()
    )
    val thread: StateFlow<ThreadDetailViewState> get() = _thread

    init {
        getThread(threadId = threadId)
    }

    fun sendComment(comment: NewComment, threadId: String) {
        viewModelScope.launch {
            repository.addComment(threadId, comment).collect {
                getThread(threadId = threadId)
            }
        }
    }


    fun getThread(threadId: String) {
        println(threadId)
        viewModelScope.launch {
            repository.getThreadDetail(threadId).collect { threads ->
                println("Collect")
                _thread.value = _thread.value.copy(
                    thread = threads,
                    isLoading = false
                )
            }
        }
    }
}

data class ThreadDetailViewState(
    var thread: Threads? = null,
    var isLoading: Boolean = true,
)