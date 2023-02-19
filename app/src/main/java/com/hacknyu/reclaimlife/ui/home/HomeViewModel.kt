package com.hacknyu.reclaimlife.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacknyu.reclaimlife.data.Repository
import com.hacknyu.reclaimlife.model.Activities
import com.hacknyu.reclaimlife.model.GroupType
import com.hacknyu.reclaimlife.model.PhysicalActivityLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
): ViewModel() {

    private val _homeViewState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val homeViewState: StateFlow<HomeViewState> get() = _homeViewState

    init {
        getDaysOfSober()
        getCurrentWeekSober()
        getTodaysActivity()
    }

    private fun getTodaysActivity() {
        viewModelScope.launch {
            _homeViewState.value = _homeViewState.value.copy(
                todaysChallenge = repository.getRandomActivity()
            )
        }
    }

    //TODO: Implement days of sober
    private fun getDaysOfSober() {
        _homeViewState.value = _homeViewState.value.copy(
            daysOfSober = 4
        )
    }

    //TODO: implement currentWeekSober
    private fun getCurrentWeekSober() {
        _homeViewState.value = _homeViewState.value.copy(
            currentWeekSober = listOf(1, 2, 3, 4, 5, 6, 7)
        )
    }
}

data class HomeViewState(
    var daysOfSober: Int = 7,
    var currentWeekSober: List<Int> = listOf(),
    var todaysChallenge: Activities = Activities("",PhysicalActivityLevel.INTENSE, GroupType.GROUP)
)