package com.example.dancestudiokisti.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dancestudiokisti.BaseFragment
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.EntryFragmentBinding
import javax.inject.Inject

class EntryFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory : EntryViewModelFactory

    private var entryFragmentBinding: EntryFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.entryComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.entry_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = EntryFragmentBinding.bind(view)
        entryFragmentBinding = binding

        val entryViewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        binding.buttonLogin.setOnClickListener {
            val action = EntryFragmentDirections.actionEntryFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.buttonRegistration.setOnClickListener {
            val action = EntryFragmentDirections.actionEntryFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }

        entryViewModel.haveToken.observe(viewLifecycleOwner, {haveToken ->
            if (haveToken == true) {
                val action = EntryFragmentDirections.actionEntryFragmentToSectionsListFragment()
                findNavController().navigate(action)
            }
        })
    }
}