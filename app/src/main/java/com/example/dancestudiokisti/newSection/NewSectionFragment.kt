package com.example.dancestudiokisti.newSection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.Keyboard
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.NewSectionFragmentBinding
import com.example.dancestudiokisti.imagePicker.ImageFragment
import com.example.dancestudiokisti.views.Toolbars
import javax.inject.Inject

class NewSectionFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NewSectionViewModelFactory

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
            NewSectionViewModel::class.java
        )

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

        newSectionViewModel.chooseImageError.observe(
            viewLifecycleOwner,
            { chooseImageError: Boolean ->
                if (chooseImageError) {
                    Toast.makeText(
                        context,
                        getString(R.string.please_choose_image),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

        val ivSelectedImage: ImageView = binding.showSelectedImage


        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            ImageFragment.EXTRA_SELECTED_LINK
        )?.observe(viewLifecycleOwner) { result ->
            selectedImageUrl = result
            Glide.with(view.context)
                .load(selectedImageUrl)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(12)))
                .into(ivSelectedImage)
            ivSelectedImage.visibility = View.VISIBLE

        //                Toast.makeText(context, getString(R.string.image_set_successfully), Toast.LENGTH_LONG).show()
        }
    }
}