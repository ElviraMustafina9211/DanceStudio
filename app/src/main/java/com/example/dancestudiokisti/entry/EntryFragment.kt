package com.example.dancestudiokisti.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.Keyboard
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.LoginFragmentBinding
import com.example.dancestudiokisti.login.LoginFragmentDirections
import com.example.dancestudiokisti.login.LoginViewModel
import com.example.dancestudiokisti.login.LoginViewModelFactory
import javax.inject.Inject

class EntryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : EntryViewModelFactory

    private var entryFragmentBinding: EntryFragmentBinding? = null

    @Inject
    lateinit var keyboard: Keyboard

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
        val binding = LoginFragmentBinding.bind(view)
        entryFragmentBinding = binding

        val entryViewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        binding.logInUser.setOnClickListener {

            val editLogin: EditText = binding.editLogin
            val userEmail = editLogin.text.toString()

            val editPassword: EditText = binding.editPassword
            val userPassword = editPassword.text.toString()
            entryViewModel.getUser(userEmail, userPassword)

            //Скрыть клавиатуру
            view.let { keyboard.hideKeyboard(it) }
        }


        entryViewModel.user.observe(viewLifecycleOwner,
            {
                Toast.makeText(activity, "Your token: ${it.userToken}", Toast.LENGTH_SHORT).show()
                val action = LoginFragmentDirections.actionLoginFragmentToSectionsListFragment()
                findNavController().navigate(action)
            })


        entryViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        entryViewModel.error.observe(viewLifecycleOwner, { error: String? ->
            if (error != null) {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.noInternetConnection.text = error
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

//        binding.noInternetConnection.setOnClickListener {
//            loginViewModel.getUser(userEmail, userPassword)
//        }
    }
}