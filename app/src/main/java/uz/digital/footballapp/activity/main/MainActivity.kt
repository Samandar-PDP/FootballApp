package uz.digital.footballapp.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.digital.footballapp.R
import uz.digital.footballapp.activity.league.LeagueActivity
import uz.digital.footballapp.adapter.LeagueAdapter
import uz.digital.footballapp.databinding.ActivityMainBinding
import uz.digital.footballapp.network.RetroInstance
import uz.digital.footballapp.repository.FootballRepository

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val leagueAdapter by lazy { LeagueAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRv()
        setupViewModel()
    }

    private fun setupRv() {
        binding.recyclerView.apply {
            adapter = leagueAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        leagueAdapter.onClick = {
            startActivity(Intent(this, LeagueActivity::class.java))
        }
    }

    private fun setupViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        viewModel.state.observe(this) {
            when (it) {
                is MainState.Loading -> {
                    binding.progress.isVisible = true
                }
                is MainState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    Log.d("@@@", "setupViewModel: ${it.error}")
                }
                is MainState.Success -> {
                    leagueAdapter.submitList(it.list)
                    Log.d("@@@", "setupViewModel: ${it.list}")
                    binding.progress.isVisible = false
                }
            }
        }
    }
}