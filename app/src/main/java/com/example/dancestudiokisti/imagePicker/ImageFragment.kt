package com.example.dancestudiokisti.imagePicker

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.databinding.ImagePickerFragmentBinding
import javax.inject.Inject

class ImageFragment : Fragment() {

    companion object {
        const val EXTRA_SELECTED_LINK = "selected_image_link"
    }

    private lateinit var binding: ImagePickerFragmentBinding

    @Inject
    lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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