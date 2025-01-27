package com.example.walmartandroidcodingassessment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartandroidcodingassessment.databinding.CountryItemViewBinding
import com.example.walmartandroidcodingassessment.model.CountryDetailsModel

internal class CountriesAdapter(
    private var entries: List<CountryDetailsModel>
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CountryItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CountryItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.binding.apply {
            countryNameText.text = entry.name
            countryRegionText.text = entry.region
            countryCodeText.text = entry.code
            capitalText.text = entry.capital
        }
    }
}