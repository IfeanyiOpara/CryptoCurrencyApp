package ifeanyi.opara.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ifeanyi.opara.cryptocurrencyapp.R
import ifeanyi.opara.cryptocurrencyapp.data.CoinDto
import ifeanyi.opara.cryptocurrencyapp.databinding.ItemCoinReviewBinding
import ifeanyi.opara.cryptocurrencyapp.models.Coin
import kotlinx.android.synthetic.main.item_coin_review.view.*

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCoinReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = differ.currentList[position]

        holder.itemView.apply {
            itemCoinName.text = current.name
            itemCoinRank.text = current.rank.toString()
            if(current.is_active) itemCoinIsActive.visibility = View.VISIBLE else itemCoinIsActive.visibility = View.INVISIBLE

            setOnClickListener {
                onItemClickListener?.let { it(current) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val itemView: ItemCoinReviewBinding) : RecyclerView.ViewHolder(itemView.root)

    //DiffUtil calculates the differences between two lists and enable us to update the items that are different,
    //instead of having to update the entire recyclerview even the ones that didn't change. And it does it in the background
    private val diffCallback = object : DiffUtil.ItemCallback<Coin>(){
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    //the list differ is the tool that would take our two lists and compare them and check the difference and
    //only update the items that would change, while running in the background
    val differ = AsyncListDiffer(this, diffCallback) // we submit our list to this differ object

    private var onItemClickListener :((Coin) -> Unit)? = null

    fun setOnItemClickListener(listener : (Coin) -> Unit){
        onItemClickListener = listener
    }

}