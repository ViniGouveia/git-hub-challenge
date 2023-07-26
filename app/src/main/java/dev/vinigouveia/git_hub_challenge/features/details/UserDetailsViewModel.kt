package dev.vinigouveia.git_hub_challenge.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vinigouveia.git_hub_challenge.data.usecase.UserDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val useCase: UserDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _screenState = MutableStateFlow(UserDetailsState())
    val screenState: StateFlow<UserDetailsState> = _screenState.asStateFlow()

    private var username: String = savedStateHandle.get<String>("username") ?: ""

    fun handleIntent(intent: UserDetailsIntent) {
        when (intent) {
            is UserDetailsIntent.FetchUserInfo -> fetchUserInfo()
            is UserDetailsIntent.SearchRepository -> searchRepository(intent.searchParam)
        }
    }

    private fun fetchUserInfo() = viewModelScope.launch {
        _screenState.emit(UserDetailsState().copy(isLoading = true))
        try {
            val userDetails = useCase.fetchUserInfo(username)
            _screenState.emit(UserDetailsState().copy(data = userDetails))
        } catch (error: Throwable) {
            _screenState.emit(UserDetailsState().copy(error = error))
        }
    }

    private fun searchRepository(searchParam: String) {
        viewModelScope.launch {
            val searchedUsers = useCase.searchRepository(searchParam)
            _screenState.emit(UserDetailsState().copy(data = searchedUsers))
        }
    }
}
