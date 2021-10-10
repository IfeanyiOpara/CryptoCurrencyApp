package ifeanyi.opara.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ifeanyi.opara.cryptocurrencyapp.databinding.ItemTeamMembersReviewBinding
import ifeanyi.opara.cryptocurrencyapp.models.TeamMember
import kotlinx.android.synthetic.main.item_team_members_review.view.*

class TeamMemberAdapter : RecyclerView.Adapter<TeamMemberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamMembersReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = differ.currentList[position]

        holder.itemView.apply {
            itemTeamName.text = current.name
            itemTeamPosition.text = current.position
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(itemView: ItemTeamMembersReviewBinding) : RecyclerView.ViewHolder(itemView.root)

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<TeamMember>() {
        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallBack)

}