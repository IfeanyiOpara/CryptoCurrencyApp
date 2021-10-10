package ifeanyi.opara.cryptocurrencyapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ifeanyi.opara.cryptocurrencyapp.data.toCoin
import ifeanyi.opara.cryptocurrencyapp.models.Coin
import ifeanyi.opara.cryptocurrencyapp.mainRepository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoinViewModel @Inject constructor(
    private val repository: CoinRepository
) :ViewModel() {


    var coins: MutableLiveData<List<Coin>>

    init {
        coins = MutableLiveData()
        getCoins()
    }

    fun getCoinsObserver() : MutableLiveData<List<Coin>>{
        return coins
    }


    fun getCoins(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCoins().map { it.toCoin() }
            coins.postValue(response)
        }
    }


}