package com.example.jsondatalocaldbapp.model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsondatalocaldbapp.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

private const val TAG = "UnitSearchAdapter"

class UnitSearchAdapter(
    data: OrderedRealmCollection<Activities>?,
    autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Activities, UnitSearchAdapter.UnitViewHolder>(data, autoUpdate) {

    private lateinit var blocks: List<Block>

    fun setList(blocks: List<Block>) {
        this.blocks = blocks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_item_layout, parent, false)
        return UnitViewHolder(view)
    }

    override fun onBindViewHolder(holder: UnitViewHolder, position: Int) {
        val activity: Activities? = data?.get(position)
        Log.d(TAG, "onBindViewHolder: activity == $activity")
        holder.activityName.text = activity?.activity_name
        holder.stepName.text = activity?.step_name
        holder.progressValueTextView.text = activity?.progress.toString()
        holder.progressBar.progress = activity?.progress!!
        holder.getStepNameTextView()

    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${data?.size!!}")
        return data?.size!!
    }

    class UnitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stepName: TextView = itemView.findViewById(R.id.step_name_text_view)
        val activityName: TextView = itemView.findViewById(R.id.activity_name_text_view)
        val progressBar: ProgressBar = itemView.findViewById(R.id.activity_progress_bar)
        val progressValueTextView: TextView =
            itemView.findViewById(R.id.activity_progress_text_view)

        fun getStepNameTextView(): TextView {
            Log.d(TAG, "getStepNameTextView: ${stepName.text}")
            return stepName
        }
    }

}