package com.hacknyu.reclaimlife.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.ui.community.CommunityViewModel
import com.hacknyu.reclaimlife.ui.home.HomeViewModel
import com.hacknyu.reclaimlife.ui.motivation.MotivationViewModel
import com.hacknyu.reclaimlife.ui.register.signin.SignInViewModel
import com.hacknyu.reclaimlife.ui.register.signup.SignUpViewModel
import com.hacknyu.reclaimlife.ui.threaddetail.ThreadDetailViewModel

class ViewModelFactory(private val repository: Repository, private val threadId: String = ""): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(MotivationViewModel::class.java)) {
            return MotivationViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            return CommunityViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ThreadDetailViewModel::class.java)) {
            return ThreadDetailViewModel(repository, threadId = threadId ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}