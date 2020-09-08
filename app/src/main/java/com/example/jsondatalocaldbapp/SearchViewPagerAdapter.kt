@file:Suppress("DEPRECATION")

package com.example.jsondatalocaldbapp

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jsondatalocaldbapp.model.Units
import io.realm.RealmList

class SearchViewPagerAdapter(
    fm: FragmentManager,
    private var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    private lateinit var units: RealmList<Units>
    private lateinit var tabNameList: ArrayList<String>

    override fun getCount(): Int {
        Log.d("PagerAdapter", "getCount: $totalTabs")
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        Log.d("PagerAdapter", "getItem: $position")
//        var fragment: Fragment? = null
//
//        when (position) {
//            in 0..position -> fragment = SearchRecyclerFragment(units)
//        }

        return when (position) {
            0 -> SearchRecyclerFragment(units).newInstance(0, units)
            1 -> SearchRecyclerFragment(units).newInstance(1, units)
            2 -> SearchRecyclerFragment(units).newInstance(2, units)
            else -> SearchRecyclerFragment(units).newInstance(3, units)
        }
    }

    fun setSearchItem(units: RealmList<Units>, tabNameList: ArrayList<String>) {
        this.units = units
        this.tabNameList = tabNameList
        Log.d("PagerAdapter", "setSearchItem: $units")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNameList[position]
    }

}