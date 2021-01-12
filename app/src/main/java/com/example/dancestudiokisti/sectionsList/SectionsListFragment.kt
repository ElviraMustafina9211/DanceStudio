package com.example.dancestudiokisti.sectionsList

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.ListSectionsFragmentBinding
import com.example.dancestudiokisti.newSection.Section
import kotlinx.android.synthetic.main.list_sections_fragment.*
import javax.inject.Inject


class SectionsListFragment : Fragment() {

    private var listSectionsFragmentBinding: ListSectionsFragmentBinding? = null

    @Inject
    lateinit var sectionsListViewModel: SectionsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //каждый раз пересоздает ViewModel, ViewModel НЕ пересоздается - это правило
        Injector.instance.inject(this)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_sections_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ListSectionsFragmentBinding.bind(view)
        listSectionsFragmentBinding = binding
//                    title = "Список секций";


//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)


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
                    SectionsListFragmentDirections.actionSectionsListFragmentToStudentsListFragment(
                        section.name
                    )
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

//        binding.fabAddSection.setOnClickListener {
//            val intent = Intent(this, NewSectionFragment::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
//            startActivity(intent)
//        }

        swipeRefreshLayout.setOnRefreshListener {
            sectionsListViewModel.getSectionsList()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }

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
        val currentNightMode =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}