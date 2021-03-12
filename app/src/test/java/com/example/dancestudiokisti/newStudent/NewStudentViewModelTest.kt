package com.example.dancestudiokisti.newStudent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dancestudiokisti.RxImmediateSchedulerRule
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.TokenSaver
import com.example.dancestudiokisti.newSection.Section
import com.example.dancestudiokisti.sectionsList.SectionsListRepositoryImpl
import com.example.dancestudiokisti.sectionsList.SectionsListViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class NewStudentViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun `getSections - sections retrieved`() {

        // Arrange
        val expectedSections = listOf(
            Section(
                "Stretching",
                "65415F0C-FDC2-4EE0-B0DC-CD2CE5959E32",
                "https://backendlessappcontent.com/49B0F014-9A2B-0420-FFBA-A5382B5FDA00/D584F591-91A7-4457-AB0D-E67686CC207A/files/Images/stretcher.png"
            )
        )

        val mockRepository = mockk<SectionsListRepositoryImpl> {
            every { getSectionsList() } returns Single.just(expectedSections)
        }

        val mockTokenSaver = mockk<TokenSaver> {
        }

        val sectionsListViewModel = SectionsListViewModel(mockRepository, mockTokenSaver)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        sectionsListViewModel.isLoading.observeForever(mockLoadingObserver)
        sectionsListViewModel.error.observeForever(mockErrorObserver)

        // Act
        sectionsListViewModel.getSections()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verify {
            mockErrorObserver.onChanged(false)
        }
        assert(sectionsListViewModel.sections.value == expectedSections)
    }

    @Test
    fun createStudent() {

        // Arrange
        val expectedStudent = listOf(
            Student(
                "Stretching",
                "Mustafina Elvira",
                "",
                true,
                8,
                8
            )
        )

        val mockNewStudentRepository = mockk<NewStudentRepository> {
            every { createStudent(student: Student) }returns Single.error(Exception())
        }

        val mockSectionsListRepository = mockk<SectionsListRepositoryImpl> {
            every { getSectionsList() } returns Single.just()
        }

        val newStudentViewModel = NewStudentViewModel(mockNewStudentRepository, mockSectionsListRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newStudentViewModel.isLoading.observeForever(mockLoadingObserver)
        newStudentViewModel.error.observeForever(mockErrorObserver)

        // Act
        newStudentViewModel.createStudent()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verifyOrder {
            mockErrorObserver.onChanged(false)
            mockErrorObserver.onChanged(true)
        }
        assert(newStudentViewModel.student().value == expectedStudent)
    }
}