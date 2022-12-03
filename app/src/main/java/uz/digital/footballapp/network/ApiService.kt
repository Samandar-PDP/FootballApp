package uz.digital.footballapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.digital.footballapp.model.ClubsDTO
import uz.digital.footballapp.model.FootballDTO

interface ApiService {
    @GET("?action=get_countries")
    suspend fun getAllLeagues(
        @Query("APIkey") key: String = "b957d00d70daeba4adf797bcefd8c499596ba0527ed938a6ad721435e68c84b2"
    ): Response<FootballDTO>
    @GET("?action=get_countries")
    suspend fun getClubs(
        @Query("league_id") id: String = "302",
        @Query("APIkey") key: String = "b957d00d70daeba4adf797bcefd8c499596ba0527ed938a6ad721435e68c84b2"
    ): Response<ClubsDTO>
}