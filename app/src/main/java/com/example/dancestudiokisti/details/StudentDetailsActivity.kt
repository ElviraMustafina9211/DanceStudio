package com.example.dancestudiokisti.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.databinding.StudentDetailsActivityBinding
import javax.inject.Inject


class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: StudentDetailsActivityBinding

    @Inject
    lateinit var studentDetailsViewModel: StudentDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StudentDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.student_details_title)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val objectId = intent.getStringExtra("objectId")

        //При нажатии на кнопку "Отправить сообщение" откроется экран для отправки SMS
        binding.sendMessage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:")
            intent.putExtra(Intent.EXTRA_TEXT, "Для продолжения занятий, необходимо приобрести абонемент")
            startActivity(intent)
        }

        Injector.instance.inject(this)

        if (objectId != null) {
            studentDetailsViewModel.getStudentDetails(objectId)
        }

        //Подписка на LiveData с деталями по студенту
        studentDetailsViewModel.detailsLiveData.observe(this, { studentDetails: Student ->
            binding.sectionName.text = studentDetails.sectionName
            binding.studentName.text = studentDetails.fullName
            binding.numberOfLessons.text = studentDetails.numberOfLessons.toString()
            binding.checkPayment.isChecked = studentDetails.wasPayed
            binding.balance.text = studentDetails.balanceOfLessons.toString()
        })

        //При нажатии на кнопку "Сохранить"
        binding.saveStudentDetailsButton.setOnClickListener {
            val numberOfLessons = binding.numberOfLessons.text.toString().toInt()
            val payment = binding.checkPayment.isChecked
            val balanceLessons = binding.balance.text.toString().toInt()

            studentDetailsViewModel.updateStudentDetails(numberOfLessons, payment, balanceLessons)

            //Скрыть клавиатуру
            val view = this.currentFocus
            view?.let { v ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
        }

        studentDetailsViewModel.closeScreen.observe(this, { closeScreen: Boolean ->
            if (closeScreen) {
                finish()
            }
        })

        //При нажатии на кнопку "Удалить"
        binding.deleteStudentDetailsButton.setOnClickListener {
            studentDetailsViewModel.deleteStudentDetails()

            //Скрыть клавиатуру
            val view = this.currentFocus
            view?.let { v ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }

        //Установить/скрыть loading indicator
        studentDetailsViewModel.isLoading.observe(this, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        //При отсутствии интернета показывать/скрывать сообщение
        // "Нет подключения к интернету, повторить загрузку?"
        studentDetailsViewModel.error.observe(this, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        //При нажатии на "Нет подключения к интернету, повторить загрузку?"
        // и включении инета юзером, выполнить еще раз запрос
        binding.noInternetConnection.setOnClickListener {
            if (objectId != null) {
                studentDetailsViewModel.getStudentDetails(objectId)
            }
        }

        //При нажатии на + или -
        binding.plus.setOnClickListener { studentDetailsViewModel.onPlusNumberOfLessonsClicked() }
        binding.minus.setOnClickListener { studentDetailsViewModel.onMinusNumberOfLessonsClicked() }
        binding.balancePlus.setOnClickListener { studentDetailsViewModel.onBalancePlusClicked() }
        binding.balanceMinus.setOnClickListener { studentDetailsViewModel.onBalanceMinusClicked() }

        binding.checkPayment.setOnCheckedChangeListener { _, isChecked ->
            studentDetailsViewModel.onPaymentChanged(isChecked)
        }
    }
}