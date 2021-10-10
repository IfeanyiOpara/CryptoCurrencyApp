package ifeanyi.opara.cryptocurrencyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ifeanyi.opara.cryptocurrencyapp.data.api.CoinPaprikaApi
import ifeanyi.opara.cryptocurrencyapp.repositoryImplementation.CoinRepositoryImplementation
import ifeanyi.opara.cryptocurrencyapp.mainRepository.CoinRepository
import ifeanyi.opara.cryptocurrencyapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImplementation(api)
    }
}