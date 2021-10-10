package ifeanyi.opara.cryptocurrencyapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ifeanyi.opara.cryptocurrencyapp.data.toCoinDetail
import ifeanyi.opara.cryptocurrencyapp.mainRepository.CoinRepository
import ifeanyi.opara.cryptocurrencyapp.models.CoinDetail
import ifeanyi.opara.cryptocurrencyapp.repositoryImplementation.CoinRepositoryImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
        private val repository : CoinRepository
) : ViewModel() {

   private var coinDetail : MutableLiveData<CoinDetail> = MutableLiveData()

    init {
        coinDetailObserver()
    }

    fun coinDetailObserver() : MutableLiveData<CoinDetail>{
        return coinDetail
    }

   fun getCoinDetail(id : String){
       viewModelScope.launch(Dispatchers.IO) {
           val response = repository.getCoinDetail(id).toCoinDetail()
           coinDetail.postValue(response)
       }
   }

}