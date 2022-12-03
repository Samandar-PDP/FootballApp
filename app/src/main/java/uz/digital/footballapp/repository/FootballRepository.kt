package uz.digital.footballapp.repository

import uz.digital.footballapp.network.ApiService

class FootballRepository(
    private val service: ApiService
) {
    suspend fun getAllLeagues() = service.getAllLeagues()
    suspend fun getClub(id: String) = service.getClubs(id)
}