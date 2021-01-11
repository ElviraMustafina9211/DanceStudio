package com.example.dancestudiokisti.imagePicker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.databinding.ImagePickerFragmentBinding
import com.example.dancestudiokisti.databinding.NewSectionFragmentBinding
import com.example.dancestudiokisti.newSection.NewSectionViewModel
import javax.inject.Inject

class ImageFragment : Fragment() {

    companion object {
        const val EXTRA_SELECTED_LINK = "selected_image_link"
    }

    private var imagePickerFragmentBinding: ImagePickerFragmentBinding? = null

    @Inject
    lateinit var imageViewModel: ImageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //каждый раз пересоздает ViewModel, ViewModel НЕ пересоздается - это правило
        Injector.instance.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.image_picker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = ImagePickerFragmentBinding.bind(view)
        imagePickerFragmentBinding = binding

//        binding = ImagePickerActivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        title = "Выбор картинки для секции"
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Injector.instance.inject(this)

        imageViewModel.getImageList()

        val recyclerView = binding.recyclerviewImagePickerFragment
        val imageAdapter = ImageAdapter { selectedLink ->
            val intent = Intent()
            intent.putExtra(EXTRA_SELECTED_LINK, selectedLink)
//            setResult(RESULT_OK, intent)
//            finish()
        }

        recyclerView.adapter = imageAdapter

        imageViewModel.imagesLiveData.observe(this, { images: List<Image> ->
            imageAdapter.setImages(images)
        })

        imageViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        imageViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        binding.noInternetConnection.setOnClickListener {
            imageViewModel.getImageList()
        }

        imageViewModel.closeScreen.observe(this, { closeScreen: Boolean ->
            if (closeScreen) {
//                finish()
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }
        })
    }
}