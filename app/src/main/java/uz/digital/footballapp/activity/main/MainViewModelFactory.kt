package uz.digital.footballapp.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.digital.footballapp.repository.FootballRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository: FootballRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}