package uz.digital.footballapp.activity.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.digital.footballapp.repository.FootballRepository

class LeagueViewModel(
    private val repository: FootballRepository
) : ViewModel() {
    private val _state: MutableLiveData<LeagueState> = MutableLiveData()
    val state: LiveData<LeagueState> get() = _state

    fun getClubById(id: String) {
        viewModelScope.launch {
            _state.postValue(LeagueState.Loading)
            try {
                val response = repository.getClub(id)
                if (response.isSuccessful) {
                    _state.postValue(LeagueState.Success(response.body()!!))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.postValue(LeagueState.Error(e.message!!))
            }
        }
    }
}