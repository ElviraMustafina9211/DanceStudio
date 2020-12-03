package com.example.dancestudiokisti.sectionsList

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.ListSectionsActivityBinding
import com.example.dancestudiokisti.newSection.NewSectionActivity
import com.example.dancestudiokisti.newSection.Section
import kotlinx.android.synthetic.main.list_sections_activity.*
import javax.inject.Inject


class SectionsListActivity : AppCompatActivity() {

    private lateinit var binding: ListSectionsActivityBinding

    @Inject
    lateinit var sectionsListViewModel: SectionsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListSectionsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.sections_list_title)
        setSupportActionBar(binding.toolbar)

        Injector.instance.inject(this)

        sectionsListViewModel.getSectionsList()

        val recyclerView = binding.recyclerviewListSectionsActivity
        val sectionsListAdapter = SectionsListAdapter { section ->
            sectionsListViewModel.deleteOneSection(section.objectId)
        }

        recyclerView.adapter = sectionsListAdapter

        sectionsListViewModel.sectionsList.observe(this, { sectionsList: List<Section> ->
            sectionsListAdapter.setSections(sectionsList)
        })

        sectionsListViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        sectionsListViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        binding.noInternetConnection.setOnClickListener {
            sectionsListViewModel.getSectionsList()
        }

        binding.fabAddSection.setOnClickListener {
            val intent = Intent(this, NewSectionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }

        swipeRefreshLayout.setOnRefreshListener {
            sectionsListViewModel.getSectionsList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_mode -> {
                switchMode()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun switchMode() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}