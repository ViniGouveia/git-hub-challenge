package dev.vinigouveia.git_hub_challenge.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vinigouveia.git_hub_challenge.data.usecase.HomeUseCase
import dev.vinigouveia.git_hub_challenge.utils.getErrorMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(HomeState().copy(isLoading = true))
    val screenState: StateFlow<HomeState> = _screenState.asStateFlow()

    fun handleIntent(action: HomeIntent) {
        when (action) {
            is HomeIntent.FetchUsers -> getUsers()
            is HomeIntent.SearchUser -> searchUsers(action.username)
        }
    }

    private fun getUsers() = viewModelScope.launch {
        try {
            val usersList = useCase.fetchUsers()
            if (usersList.isNotEmpty()) _screenState.emit(HomeState().copy(data = usersList))
            else _screenState.emit(HomeState())
        } catch (error: Throwable) {
            _screenState.emit(HomeState().copy(error = error.getErrorMessage()))
        }
    }

    private fun searchUsers(searchParam: String) {
        viewModelScope.launch {
            val searchedUsers = useCase.searchUser(searchParam)
            _screenState.emit(HomeState().copy(data = searchedUsers))
        }
    }

}
