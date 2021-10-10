package ifeanyi.opara.cryptocurrencyapp.mainRepository

import ifeanyi.opara.cryptocurrencyapp.data.CoinDetailDto
import ifeanyi.opara.cryptocurrencyapp.data.CoinDto

interface CoinRepository {

    suspend fun getCoins() : List<CoinDto>

    suspend fun getCoinDetail(coinId : String) : CoinDetailDto

}