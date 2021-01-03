package com.example.dancestudiokisti.newSection

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.databinding.NewSectionFragmentBinding
import javax.inject.Inject

class NewSectionFragment : Fragment() {

    companion object {
        private const val NEW_IMAGE_ACTIVITY_REQUEST_CODE = 1
    }

    private var selectedImageUrl: String? = null

    private lateinit var binding: NewSectionFragmentBinding

    @Inject
    lateinit var newSectionViewModel: NewSectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = NewSectionActivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        title = getString(R.string.new_section_title)
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Injector.instance.inject(this)

        binding.imagePicker.setOnClickListener {
//            val intent = Intent(this, ImageActivity::class.java)
//            startActivityForResult(intent, NEW_IMAGE_ACTIVITY_REQUEST_CODE)
        }

        binding.saveSectionButton.setOnClickListener {
            val editSection: EditText = binding.editText
            val sectionName = editSection.text.toString()
            newSectionViewModel.createSection(sectionName, selectedImageUrl)

//            val view = this.currentFocus
//            view?.let { v ->
//                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//                imm?.hideSoftInputFromWindow(v.windowToken, 0)
//            }
        }
        newSectionViewModel.closeScreen.observe(this, { closeScreen: Boolean ->
            if (closeScreen) {
//                finish()
            }
        })
        newSectionViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        newSectionViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == NEW_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            selectedImageUrl = data?.getStringExtra(ImageActivity.EXTRA_SELECTED_LINK)
//            Toast.makeText(
//                applicationContext,
//                "Картинка установлена успешно",
//                Toast.LENGTH_LONG
//            ).show()
//        } else {
//            Toast.makeText(
//                applicationContext,
//                "Не выбрана картинка",
//                Toast.LENGTH_LONG
//            ).show()
//        }
    }
}