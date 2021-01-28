package com.example.dancestudiokisti.sectionsList

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.ListSectionsFragmentBinding
import com.example.dancestudiokisti.newSection.Section
import kotlinx.android.synthetic.main.list_sections_fragment.*
import javax.inject.Inject


class SectionsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : SectionsListViewModelFactory

    private var listSectionsFragmentBinding: ListSectionsFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.instance.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_sections_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ListSectionsFragmentBinding.bind(view)
        listSectionsFragmentBinding = binding
        binding.appbar.toolbar.title = getString(R.string.sections_list_title)
        binding.appbar.toolbar.inflateMenu(R.menu.menu)

        val sectionsListViewModel = ViewModelProvider(this, viewModelFactory).get(
            SectionsListViewModel::class.java)

        binding.fabAddSection.setOnClickListener {
            val action = SectionsListFragmentDirections.actionSectionsListFragmentToNewSectionFragment()
            findNavController().navigate(action)
        }

        sectionsListViewModel.getSectionsList()

        val recyclerView = binding.recyclerviewListSectionsFragment
        val sectionsListAdapter = SectionsListAdapter(object : OnSectionLongPressListener {
            override fun onLongPressed(section: Section) {
                sectionsListViewModel.deleteOneSection(section.objectId)
            }
        }, object : OnSectionClickListener {
            override fun onClick(section: Section) {
                val action =
                    SectionsListFragmentDirections.actionSectionsListFragmentToStudentsListFragment(section.name)
                findNavController().navigate(action)
            }
        })

        recyclerView.adapter = sectionsListAdapter

        sectionsListViewModel.sectionsList.observe(
            viewLifecycleOwner,
            { sectionsList: List<Section> ->
                sectionsListAdapter.setSections(sectionsList)
            })

        sectionsListViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false
            }
        })

        sectionsListViewModel.error.observe(viewLifecycleOwner, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        binding.noInternetConnection.setOnClickListener {
            sectionsListViewModel.getSectionsList()
        }

        swipeRefreshLayout.setOnRefreshListener {
            sectionsListViewModel.getSectionsList()
        }

        binding.appbar.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.change_mode -> {
                    switchMode()
                    true
                }
                else -> false
            }
        }
    }

    private fun switchMode() {
        val currentNightMode =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}