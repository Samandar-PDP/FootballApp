package uz.digital.footballapp.activity.league

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.digital.footballapp.repository.FootballRepository

@Suppress("UNCHECKED_CAST")
class LeagueViewModelFactory(
    private val repository: FootballRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LeagueViewModel(repository) as T
    }
}