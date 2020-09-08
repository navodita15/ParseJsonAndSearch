package com.example.jsondatalocaldbapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsondatalocaldbapp.model.Units
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

private const val TAG = "SearchAdapter"

class SearchAdapter(
    private val context : Context,
    data: OrderedRealmCollection<Units>?,
    autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Units, SearchAdapter.SearchViewHolder>(data, autoUpdate) {


    private lateinit var units: List<Units>

    fun setList(units: List<Units>) {
        this.units = units
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val unit: Units? = data?.get(position)
        Log.d(TAG, "onBindViewHolder: unit == $unit")
        holder.unitText.text = unit?.id
        val unitAdapter = UnitSearchAdapter(unit?.activities, true)
        holder.searchUnitRecyclerView.adapter = unitAdapter
        holder.searchUnitRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val phaseText: TextView = itemView.findViewById(R.id.phase_number)
        val unitText: TextView = itemView.findViewById(R.id.unit_number)
        val searchUnitRecyclerView: RecyclerView =
            itemView.findViewById(R.id.search_result_recycler_view)
    }

}