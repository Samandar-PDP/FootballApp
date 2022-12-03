package uz.digital.footballapp.activity.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import uz.digital.footballapp.R
import uz.digital.footballapp.network.RetroInstance
import uz.digital.footballapp.repository.FootballRepository
import kotlin.math.log

class LeagueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
        setupViewModel()
    }

    private fun setupViewModel() {
        val apiService = RetroInstance.retroInstance()
        val repository = FootballRepository(apiService)
        val viewModel = ViewModelProvider(this, LeagueViewModelFactory(repository))[LeagueViewModel::class.java]
        viewModel.getClubById("302")
        viewModel.state.observe(this) {
            when(it) {
                LeagueState.Loading -> {

                }
                is LeagueState.Error -> {

                }
                is LeagueState.Success -> {
                    Log.d("@@@Success", "setupViewModel: ${it.list}")
                }
            }
        }
    }
}