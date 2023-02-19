package com.hacknyu.reclaimlife.ui.register.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

enum class SignUpState {
    SUCCESSFUL,
    FAILED,
    NONE
}

class SignUpViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _isSignUpSuccessful: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.NONE)
    val isSignUpSuccessful: StateFlow<SignUpState> get() = _isSignUpSuccessful

    fun signUp(user: User) {
        viewModelScope.launch {
            repository.registerUser(user).collect { isSuccessful ->
                if (isSuccessful) {
                    _isSignUpSuccessful.value = SignUpState.SUCCESSFUL
                } else {
                    _isSignUpSuccessful.value = SignUpState.FAILED
                }
            }
        }
    }

    fun resetState() {
        viewModelScope.launch {
            _isSignUpSuccessful.value = SignUpState.NONE
        }
    }

}