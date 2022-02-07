package com.example.willywonka.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.willywonka.gson.Worker
import com.example.willywonka.databinding.OompaLoompaWorkersAdapterViewBinding
import com.squareup.picasso.Picasso


class OompaLoompaWorkerAdapter(
    private val workersList: List<Worker>,private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<OompaLoompaWorkerAdapter.MyViewHolder>() {
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            OompaLoompaWorkersAdapterViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvWorkerName.text = workersList[position].firstName
        holder.binding.tvWorkerLastName.text = workersList[position].lastName
        var image = workersList[position].image
        Picasso.get().load(image).into(holder.binding.imgWorker)
        holder.binding.tvWorkerProfession.text = workersList[position].profession
        holder.binding.tvWorkerCountry.text = workersList[position].country
        holder.itemView.setOnClickListener{itemClickListener.itemClickListener(workersList[position])}
    }

    override fun getItemCount(): Int {
        return workersList.size
    }

    inner class MyViewHolder(val binding: OompaLoompaWorkersAdapterViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
interface ItemClickListener {
    fun itemClickListener(worker : Worker)
}