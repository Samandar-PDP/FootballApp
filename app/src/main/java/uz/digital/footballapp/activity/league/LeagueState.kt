package uz.digital.footballapp.activity.league

import uz.digital.footballapp.model.Club

sealed class LeagueState {
    object Loading: LeagueState()
    data class Error(val error: String): LeagueState()
    data class Success(val list: List<Club>): LeagueState()
}