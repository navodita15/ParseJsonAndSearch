package com.example.jsondatalocaldbapp.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.jsondatalocaldbapp.*
import com.example.jsondatalocaldbapp.model.SearchAdapter
import com.example.jsondatalocaldbapp.model.SearchViewPagerAdapter
import com.example.jsondatalocaldbapp.model.Units
import com.example.jsondatalocaldbapp.viewmodel.DatabaseViewModel
import com.google.android.material.tabs.TabLayout
import io.realm.RealmList
import android.os.Handler as Handler1


private lateinit var splashScreenLayout: RelativeLayout
private lateinit var mainLayout: RelativeLayout
private lateinit var sampleSearchHintLayout: LinearLayout
private lateinit var searchNotFoundLayout: LinearLayout
private lateinit var searchLayout: LinearLayout
private lateinit var searchBottomUnderlineView: View
private lateinit var termNotFound: TextView
private lateinit var searchView: SearchView
private lateinit var databaseViewModel: DatabaseViewModel
private lateinit var searchAdapter: SearchAdapter
private lateinit var searchViewPager: ViewPager
private lateinit var searchTabs: TabLayout

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        databaseViewModel = ViewModelProvider(this).get(DatabaseViewModel::class.java)
        splashScreenLayout = findViewById(R.id.splash_layout)
        sampleSearchHintLayout = findViewById(R.id.sample_search_hint_layout)
        searchNotFoundLayout = findViewById(R.id.search_not_found_layout)
        searchLayout = findViewById(R.id.search_layout)
        termNotFound = findViewById(R.id.term_not_found)
        mainLayout = findViewById(R.id.main_layout)
        searchBottomUnderlineView = findViewById(R.id.search_bottom_view)
        searchViewPager = findViewById(R.id.search_viewpager_adapter)
        searchView = findViewById(R.id.search_text)
        searchTabs = findViewById(R.id.search_tabs)

        showSplashScreen()

    }

    private fun launchSearchScreen() {
        mainLayout.visibility = View.VISIBLE
        setActionBarTitle()
        setSearchView()
        searchView.setOnQueryTextListener(mOnQueryTextListener)

    }

    private val mOnQueryTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {


            override fun onQueryTextSubmit(s: String): Boolean {
                sampleSearchHintLayout.visibility = View.GONE
                getItemsFromDb(s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                if (s.isEmpty()) {
                    Log.d(TAG, "onQueryTextChange: here 1")
                    sampleSearchHintLayout.visibility = View.VISIBLE
                    searchBottomUnderlineView.setBackgroundColor(
                        resources.getColor(
                            R.color.grey,
                            theme
                        )
                    )
                    searchNotFoundLayout.visibility = View.GONE
                    searchLayout.visibility = View.GONE
                    searchTabs.removeAllTabs()
                } else {
                    Log.d(TAG, "onQueryTextChange: here 2")
                    searchNotFoundLayout.visibility = View.GONE
                    sampleSearchHintLayout.visibility = View.VISIBLE
                    searchTabs.removeAllTabs()
                    searchLayout.visibility = View.GONE
                    searchBottomUnderlineView.setBackgroundColor(
                        resources.getColor(
                            R.color.yellow,
                            theme
                        )
                    )
                }
                return true
            }
        }

    @SuppressLint("SetTextI18n")
    private fun getItemsFromDb(s: String) {
        val query = "%$s%"
        lateinit var units: RealmList<Units>
        var tabNameList: ArrayList<String> = ArrayList()
        tabNameList.add("All")
        tabNameList.addAll(databaseViewModel.getTitleList())
        Log.d(TAG, "getItemsFromDb: here")

        searchTabs.addTab(searchTabs.newTab().setText("All"))
        databaseViewModel.getSearchDb(s).observe(this, Observer { it ->
            it.forEach {
                Log.d(TAG, "getItemsFromDb: ${it.block_name}")
                searchTabs.addTab(searchTabs.newTab().setText(it.block_name))
                units = it.unit!!

            }
            searchTabs.tabGravity = TabLayout.GRAVITY_FILL


        })
        Log.d(TAG, "getItemsFromDb: end")
        val found: Boolean = databaseViewModel.searchData(s)
        if (found) {
            searchNotFoundLayout.visibility = View.GONE
            searchLayout.visibility = View.VISIBLE
            val searchViewPagerAdapter = SearchViewPagerAdapter(
                supportFragmentManager,
                tabNameList.size
            )
            searchViewPagerAdapter.setSearchItem(units, tabNameList)
            searchViewPager.adapter = searchViewPagerAdapter
            searchTabs.setupWithViewPager(searchViewPager, true)

        } else {
            searchLayout.visibility = View.GONE
            searchNotFoundLayout.visibility = View.VISIBLE
            termNotFound.text = "Term $s Not Found"
        }
    }

    private fun setSearchView() {
        val id: Int = searchView.context.resources
            .getIdentifier("android:id/search_src_text", null, null)
        val textView: EditText = searchView.findViewById(id)
        textView.setTextColor(Color.WHITE)
        val searchPlateId: Int = resources.getIdentifier("android:id/search_plate", null, null)
        val mSearchPlate: View = searchView.findViewById(searchPlateId)
        val closeButtonId: Int = resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeButton: ImageView = searchView.findViewById(closeButtonId)
        closeButton.setImageResource(R.drawable.close_button)
        closeButton.setOnClickListener {
            sampleSearchHintLayout.visibility = View.VISIBLE
            searchNotFoundLayout.visibility = View.GONE
            searchLayout.visibility = View.GONE
            searchTabs.removeAllTabs()
            textView.setText("")
        }

        mSearchPlate.setBackgroundColor(Color.TRANSPARENT)
        sampleSearchHintLayout.visibility = View.VISIBLE

    }

    private fun setActionBarTitle() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = SEARCH_TITLE;
        supportActionBar?.show()
    }

    private fun showSplashScreen() {
        val handler: Handler1 = Handler(Looper.myLooper()!!)
        splashScreenLayout.visibility = View.VISIBLE
        handler.postDelayed(
            Runnable {
                splashScreenLayout.visibility = View.GONE
                launchSearchScreen()
            },
            SPLASH_SCREEN_LOADING_TIMEOUT
        )

    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy: called")
        databaseViewModel.cleanUp()
        super.onDestroy()

    }

}

