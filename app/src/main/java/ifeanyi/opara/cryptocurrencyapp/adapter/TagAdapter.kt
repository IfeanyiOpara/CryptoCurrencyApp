package ifeanyi.opara.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ifeanyi.opara.cryptocurrencyapp.databinding.ItemTagReviewBinding
import ifeanyi.opara.cryptocurrencyapp.models.CoinDetail
import kotlinx.android.synthetic.main.item_tag_review.view.*

class TagAdapter : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTagReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = differ.currentList[position]

        holder.itemView.itemTagName.text = "#$current"
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(itemView: ItemTagReviewBinding) : RecyclerView.ViewHolder(itemView.root)

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    //provides the  current access to the list of Strings and handles the showing it on the recycler view
    val differ = AsyncListDiffer(this, diffUtilCallBack)
}