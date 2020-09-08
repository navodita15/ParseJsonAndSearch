package com.example.jsondatalocaldbapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsondatalocaldbapp.MyApplication
import com.example.jsondatalocaldbapp.R
import com.example.jsondatalocaldbapp.model.SearchAdapter
import com.example.jsondatalocaldbapp.model.Units
import io.realm.RealmList


class SearchRecyclerFragment(private val units: RealmList<Units>) : Fragment() {

    private lateinit var searchRecyclerView: RecyclerView

    var id1 = 0
    fun newInstance(id1: Int, units: RealmList<Units>): Fragment{
        val f = SearchRecyclerFragment(units)
        val args = Bundle()
        this.id1 = id1
        args.putInt("position", id1)
        f.arguments = args
        return f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search_recycler, container, false)
        val position = requireArguments().getInt("position")
        searchRecyclerView = view.findViewById(R.id.list)
        searchRecyclerView.layoutManager =
            LinearLayoutManager(MyApplication.getAppContext(), LinearLayoutManager.VERTICAL, false)
        val searchAdapter = SearchAdapter(requireContext(), units, true)
        searchRecyclerView.adapter = searchAdapter
        return view
    }


}