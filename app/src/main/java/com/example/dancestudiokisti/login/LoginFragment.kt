package com.example.dancestudiokisti.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.Keyboard
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.LoginFragmentBinding
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : LoginViewModelFactory

    private var loginFragmentBinding: LoginFragmentBinding? = null

    @Inject
    lateinit var keyboard: Keyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.loginComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = LoginFragmentBinding.bind(view)
        loginFragmentBinding = binding

        val loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.logInUser.setOnClickListener {

            val editLogin: EditText = binding.editLogin
            val userEmail = editLogin.text.toString()

            val editPassword: EditText = binding.editPassword
            val userPassword = editPassword.text.toString()
            loginViewModel.getUser(userEmail, userPassword)

            //Скрыть клавиатуру
            view.let { keyboard.hideKeyboard(it) }
        }


        loginViewModel.user.observe(viewLifecycleOwner,
            {
                val action = LoginFragmentDirections.actionLoginFragmentToSectionsListFragment()
                findNavController().navigate(action)
            })


        loginViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        loginViewModel.error.observe(viewLifecycleOwner, { error: String? ->
            if (error != null) {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.noInternetConnection.text = error
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })
    }
}