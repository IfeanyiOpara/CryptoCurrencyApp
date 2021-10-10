package ifeanyi.opara.cryptocurrencyapp.data.api

import ifeanyi.opara.cryptocurrencyapp.data.CoinDetailDto
import ifeanyi.opara.cryptocurrencyapp.data.CoinDto
import ifeanyi.opara.cryptocurrencyapp.models.CoinDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinDetail(
        @Path("coinId") coinId: String
    ) : CoinDetailDto

}