package com.example.dancestudiokisti.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dancestudiokisti.Injector
import com.example.dancestudiokisti.Keyboard
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.databinding.StudentDetailsFragmentBinding
import com.example.dancestudiokisti.views.Toolbars
import javax.inject.Inject

class StudentDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: StudentDetailsViewModelFactory

    private var studentDetailsFragmentBinding: StudentDetailsFragmentBinding? = null

    @Inject
    lateinit var keyboard: Keyboard

    private val args: StudentDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Injector.studentDetailsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.student_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = StudentDetailsFragmentBinding.bind(view)
        studentDetailsFragmentBinding = binding
        binding.appbar.toolbar.title = getString(R.string.student_details_title)
        Toolbars.enableBackButton(view, findNavController())

        val studentDetailsViewModel = ViewModelProvider(this, viewModelFactory).get(
            StudentDetailsViewModel::class.java)

        studentDetailsViewModel.getStudentDetails(args.objectId)

        //При нажатии на кнопку "Отправить сообщение" откроется экран для отправки SMS
        binding.sendMessage.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.data = Uri.parse("sms:")
            smsIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.buy_subscription))
            try {
                startActivity(smsIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Нет подходящего приложения", Toast.LENGTH_SHORT).show()
            }
        }

        //Подписка на LiveData с деталями по студенту
        studentDetailsViewModel.detailsLiveData.observe(
            viewLifecycleOwner,
            { studentDetails: Student ->
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
            keyboard.hideKeyboard(view)
            Toast.makeText(context, R.string.data_saved, Toast.LENGTH_SHORT).show()
        }

        studentDetailsViewModel.closeScreen.observe(viewLifecycleOwner, { closeScreen: Boolean ->
            if (closeScreen) {
                findNavController().navigateUp()
            }
        })

        //При нажатии на кнопку "Удалить"
        binding.deleteStudentDetailsButton.setOnClickListener {
            studentDetailsViewModel.deleteStudentDetails()
            //Скрыть клавиатуру
            keyboard.hideKeyboard(view)
        }

        //Установить/скрыть loading indicator
        studentDetailsViewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })

        //При отсутствии интернета показывать/скрывать сообщение "Нет подключения к интернету, повторить загрузку?"
        studentDetailsViewModel.error.observe(viewLifecycleOwner, { error: Boolean ->
            if (error) {
                binding.noInternetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.GONE
            }
        })

        //При нажатии на "Нет подключения к интернету, повторить загрузку?"
        // и включении инета юзером, выполнить еще раз запрос
        binding.noInternetConnection.setOnClickListener {
            studentDetailsViewModel.getStudentDetails(args.objectId)
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