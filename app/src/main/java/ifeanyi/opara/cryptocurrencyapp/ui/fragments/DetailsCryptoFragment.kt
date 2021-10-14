package ifeanyi.opara.cryptocurrencyapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ifeanyi.opara.cryptocurrencyapp.R
import ifeanyi.opara.cryptocurrencyapp.adapter.TagAdapter
import ifeanyi.opara.cryptocurrencyapp.adapter.TeamMemberAdapter
import ifeanyi.opara.cryptocurrencyapp.databinding.FragmentDetailsCryptoBinding
import ifeanyi.opara.cryptocurrencyapp.ui.viewModel.CoinDetailViewModel
import java.util.*

class DetailsCryptoFragment : Fragment(R.layout.fragment_details_crypto) {

    private val viewModel : CoinDetailViewModel by activityViewModels()

    private var _binding : FragmentDetailsCryptoBinding? = null
    private val binding get() = _binding!!

    val args : DetailsCryptoFragmentArgs by navArgs()
    lateinit var tagsAdapter: TagAdapter
    lateinit var teamMemberAdapter: TeamMemberAdapter

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsCryptoBinding.bind(view)

        tagsAdapter = TagAdapter()
        binding.coinDetailTagRv.apply {
            setHasFixedSize(true)
            adapter = tagsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        }

        teamMemberAdapter = TeamMemberAdapter()
        binding.coinDetailTeamRv.apply {
            setHasFixedSize(true)
            adapter = teamMemberAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        val coin = args.coin
        val id = coin.id
        viewModel.getCoinDetail(id)



        viewModel.coinDetailObserver().observe(viewLifecycleOwner, { coinDetail ->

            Log.d("Tag", coinDetail.tags.toString())
            Log.d("Name", coinDetail.name.toString())
            Log.d("Team", coinDetail.team.toString())
            Log.d("Description", coinDetail.description.toString())
            Log.d("Rank", coinDetail.rank.toString())
            Log.d("Symbol", coinDetail.symbol.toString())

            binding.apply {
                coinDetailName.text = coinDetail.name
                coinDetailRank.text = coinDetail.rank.toString()
                coinDetailDescription.text = coinDetail.description
                coinDetailSymbol.text = coinDetail.symbol


                if(coinDetail.isActive){
                    coinDetailActive.resources.getDrawable(R.drawable.active)
                }else{
                    coinDetailActive.resources.getDrawable(R.drawable.cancel)
                }
            }

            if(coinDetail.tags != null){
                tagsAdapter.differ.submitList(coinDetail.tags)
            }
            if(coinDetail.team != null){
                teamMemberAdapter.differ.submitList(coinDetail.team)
            }


        })

    }
}