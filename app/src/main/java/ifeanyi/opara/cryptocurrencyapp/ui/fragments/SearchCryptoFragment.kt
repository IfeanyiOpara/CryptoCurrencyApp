package ifeanyi.opara.cryptocurrencyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ifeanyi.opara.cryptocurrencyapp.R
import ifeanyi.opara.cryptocurrencyapp.databinding.FragmentSearchCryptoBinding


class SearchCryptoFragment : Fragment() {
    private var _binding: FragmentSearchCryptoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchCryptoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}