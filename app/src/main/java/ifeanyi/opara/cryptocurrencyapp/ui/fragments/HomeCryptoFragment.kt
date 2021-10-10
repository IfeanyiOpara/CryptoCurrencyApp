package ifeanyi.opara.cryptocurrencyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ifeanyi.opara.cryptocurrencyapp.R
import ifeanyi.opara.cryptocurrencyapp.adapter.CoinAdapter
import ifeanyi.opara.cryptocurrencyapp.data.toCoin
import ifeanyi.opara.cryptocurrencyapp.databinding.FragmentHomeCryptoBinding
import ifeanyi.opara.cryptocurrencyapp.ui.CoinViewModel
import ifeanyi.opara.cryptocurrencyapp.util.Resource

class HomeCryptoFragment : Fragment(R.layout.fragment_home_crypto) {

//    private val viewModel: CoinViewModel by viewModels() //specifically for the fragment
    private val viewModel: CoinViewModel by activityViewModels()// attaches it to the parent activity hosting it

    private var _binding : FragmentHomeCryptoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeCryptoBinding.bind(view)

        val adapter = CoinAdapter()

        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("coin", it) // the "coin" must be the same with the in the adapter where it is being received
            }

            findNavController().navigate(
                    R.id.action_homeCryptoFragment_to_detailsCryptoFragment,
                    bundle
            )
        }

        viewModel.getCoinsObserver().observe(viewLifecycleOwner, Observer {
            adapter.differ.submitList(it.toList())
        })


        binding.apply {
            rvHomeCrypto.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                setAdapter(adapter)
            }
        }

    }

}