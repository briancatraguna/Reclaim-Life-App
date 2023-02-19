package com.hacknyu.reclaimlife.ui.register.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

enum class SignInState {
    SUCCESSFUL,
    FAILED,
    NONE
}

class SignInViewModel(
    private val repository: Repository
): ViewModel() {

    private val _signInState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState.NONE)
    val signInState: StateFlow<SignInState> get() = _signInState

    fun signInUser(user: User) {
        viewModelScope.launch {
            repository.loginUser(user).collect { isSuccessful ->
                if (isSuccessful) {
                    _signInState.value = SignInState.SUCCESSFUL
                } else {
                    _signInState.value = SignInState.FAILED
                }
            }
        }
    }

}

