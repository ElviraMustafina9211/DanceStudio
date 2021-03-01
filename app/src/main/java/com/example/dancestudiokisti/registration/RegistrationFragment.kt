package com.example.dancestudiokisti.registration

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
import com.example.dancestudiokisti.databinding.RegistrationFragmentBinding
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: RegistrationViewModelFactory

    private var registrationFragmentBinding: RegistrationFragmentBinding? = null

    @Inject
    lateinit var keyboard: Keyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.registrationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = RegistrationFragmentBinding.bind(view)
        registrationFragmentBinding = binding

        val registrationViewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)

        binding.registerUserButton.setOnClickListener {

            val editLogin: EditText = binding.editLogin
            val userEmail = editLogin.text.toString()

            val editPassword: EditText = binding.editPassword
            val userPassword = editPassword.text.toString()

            registrationViewModel.createNewUser(userEmail, userPassword)

            //Скрыть клавиатуру
            view.let { keyboard.hideKeyboard(it) }
        }

        registrationViewModel.newUser.observe(viewLifecycleOwner,
            {
                Toast.makeText(activity, "Your token: ${it.userToken}", Toast.LENGTH_SHORT).show()
                val action = RegistrationFragmentDirections.actionRegistrationFragmentToSectionsListFragment()
                findNavController().navigate(action)
            })

        registrationViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        registrationViewModel.error.observe(viewLifecycleOwner, { error: String? ->
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