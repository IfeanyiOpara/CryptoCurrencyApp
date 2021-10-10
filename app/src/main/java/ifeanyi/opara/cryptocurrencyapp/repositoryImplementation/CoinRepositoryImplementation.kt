package ifeanyi.opara.cryptocurrencyapp.repositoryImplementation

import ifeanyi.opara.cryptocurrencyapp.data.CoinDetailDto
import ifeanyi.opara.cryptocurrencyapp.data.CoinDto
import ifeanyi.opara.cryptocurrencyapp.data.api.CoinPaprikaApi
import ifeanyi.opara.cryptocurrencyapp.mainRepository.CoinRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImplementation @Inject constructor(private val coinPaprikaApi: CoinPaprikaApi) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return coinPaprikaApi.getCoinDetail(coinId)
    }


}