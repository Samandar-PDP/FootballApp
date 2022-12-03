package uz.digital.footballapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.digital.footballapp.R
import uz.digital.footballapp.databinding.ItemLayoutBinding
import uz.digital.footballapp.model.League
import uz.digital.footballapp.network.RetroInstance

class LeagueAdapter: ListAdapter<League, LeagueAdapter.LeagueViewHolder>(DiffCallBack()) {
    lateinit var onClick: (League) -> Unit
    private class DiffCallBack: DiffUtil.ItemCallback<League>() {
        override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem.country_id == newItem.country_id
        }

        override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LeagueViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(league: League) {
            with(binding) {
                Picasso.get()
                    .load(league.country_logo.ifEmpty { RetroInstance.defaultUrl })
                    .placeholder(R.drawable.ic_baseline_outlined_flag_24)
                    .into(imageView)
                textName.text = league.country_name
                itemView.setOnClickListener {
                    onClick(league)
                }
            }
        }
    }
}