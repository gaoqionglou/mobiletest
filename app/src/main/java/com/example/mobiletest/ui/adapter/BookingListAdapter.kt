package com.example.mobiletest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.databinding.ItemBinding
import com.example.mobiletest.service.model.Segment

class BookingListAdapter : RecyclerView.Adapter<BookingListAdapter.VH>() {

    private var data = ArrayList<Segment>()
    fun setData(movieList: List<Segment>) {
        this.data = movieList as ArrayList<Segment>
        notifyDataSetChanged()
    }

    class VH(val itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        "${data[position].originAndDestinationPair.originCity} - ${data[position].originAndDestinationPair.destinationCity}".also {
            holder.itemBinding.textView.text = it
        }
    }
}