package com.example.dancestudiokisti.newSection

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
import com.example.dancestudiokisti.databinding.NewSectionFragmentBinding
import com.example.dancestudiokisti.imagePicker.ImageFragment
import com.example.dancestudiokisti.views.Toolbars
import javax.inject.Inject

class NewSectionFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : NewSectionViewModelFactory

    private var selectedImageUrl: String? = null

    private var newSectionFragmentBinding: NewSectionFragmentBinding? = null

    @Inject
    lateinit var keyboard: Keyboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.instance.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_section_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = NewSectionFragmentBinding.bind(view)
        newSectionFragmentBinding = binding
        binding.appbar.toolbar.title = getString(R.string.new_section_title)
        Toolbars.enableBackButton(view, findNavController())

        val newSectionViewModel = ViewModelProvider(this, viewModelFactory).get(
            NewSectionViewModel::class.java)

        binding.saveSectionButton.setOnClickListener {
            val editSection: EditText = binding.editText
            val sectionName = editSection.text.toString()
            newSectionViewModel.createSection(sectionName, selectedImageUrl)
            //Скрыть клавиатуру
            keyboard.hideKeyboard(view)
        }


        binding.imagePicker.setOnClickListener {
            //Скрыть клавиатуру
            keyboard.hideKeyboard(view)

            val action = NewSectionFragmentDirections.actionNewSectionFragmentToImageFragment()
            findNavController().navigate(action)
        }

        newSectionViewModel.closeScreen.observe(viewLifecycleOwner, { closeScreen: Boolean ->
            if (closeScreen) {
                findNavController().navigateUp()
            }
        })
        newSectionViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        newSectionViewModel.error.observe(viewLifecycleOwner, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })



        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            ImageFragment.EXTRA_SELECTED_LINK
        )?.observe(viewLifecycleOwner) { result ->
                selectedImageUrl = result
                Toast.makeText(context, "Картинка установлена успешно", Toast.LENGTH_LONG).show()
        }
    }



//     fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == NEW_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            selectedImageUrl = data?.getStringExtra(ImageFragment.EXTRA_SELECTED_LINK)
//            Toast.makeText(context, "Картинка установлена успешно", Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(context, "Не выбрана картинка", Toast.LENGTH_LONG).show()
//        }
//    }
}