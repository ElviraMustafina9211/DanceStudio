package com.example.dancestudiokisti.newStudent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dancestudiokisti.RxImmediateSchedulerRule
import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.newSection.Section
import com.example.dancestudiokisti.sectionsList.SectionsListRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
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
        val expectedSectionName = "Stretching"
        val expectedSections = listOf(
            Section(
                expectedSectionName,
                "65415F0C-FDC2-4EE0-B0DC-CD2CE5959E32",
                "https://backendlessappcontent.com/49B0F014-9A2B-0420-FFBA-A5382B5FDA00/D584F591-91A7-4457-AB0D-E67686CC207A/files/Images/stretcher.png"
            )
        )

        val expectedSectionNames = listOf(expectedSectionName)

        val mockRepository = mockk<SectionsListRepository> {
            every { getSectionsList() } returns Single.just(expectedSections)
        }

        val mockNewStudentRepository = mockk<NewStudentRepository>()

        val newStudentViewModel = NewStudentViewModel(mockNewStudentRepository, mockRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newStudentViewModel.isLoading.observeForever(mockLoadingObserver)
        newStudentViewModel.error.observeForever(mockErrorObserver)

        // Act
        newStudentViewModel.getSections()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verify {
            mockErrorObserver.onChanged(false)
        }
        assertThat(newStudentViewModel.sectionNames.value).isEqualTo(expectedSectionNames)
    }

    @Test
    fun `getSections - error occurred`() {

        // Arrange
        val mockRepository = mockk<SectionsListRepository> {
            every { getSectionsList() } returns Single.error(Exception())
        }

        val mockNewStudentRepository = mockk<NewStudentRepository>()

        val newStudentViewModel = NewStudentViewModel(mockNewStudentRepository, mockRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newStudentViewModel.isLoading.observeForever(mockLoadingObserver)
        newStudentViewModel.error.observeForever(mockErrorObserver)

        // Act
        newStudentViewModel.getSections()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verifyOrder {
            mockErrorObserver.onChanged(false)
            mockErrorObserver.onChanged(true)
        }
        assert(newStudentViewModel.sectionNames.value == null)
    }

    @Test
    fun createStudent() {

        // Arrange
        val expectedSectionName = "Stretching"
        val expectedFullName = "Mustafina Elvira"
        val expectedObjectId = ""
        val expectedWasPayed = true
        val expectedNumberOfLessons = 9
        val expectedBalanceOfLessons = 9


        val expectedStudent =
            Student(
                expectedSectionName,
                expectedFullName,
                expectedObjectId,
                expectedWasPayed,
                expectedNumberOfLessons,
                expectedBalanceOfLessons)

        val mockNewStudentRepository = mockk<NewStudentRepository> {
            every { createStudent(expectedStudent) } returns Single.just(expectedStudent)
        }

        val mockSectionsListRepository = mockk<SectionsListRepository> {
        }

        val newStudentViewModel =
            NewStudentViewModel(mockNewStudentRepository, mockSectionsListRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newStudentViewModel.isLoading.observeForever(mockLoadingObserver)
        newStudentViewModel.error.observeForever(mockErrorObserver)

        // Act
        newStudentViewModel.createStudent(
            expectedFullName,
            expectedSectionName,
            expectedNumberOfLessons,
            expectedWasPayed,
            expectedBalanceOfLessons)

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verify{mockNewStudentRepository.createStudent(expectedStudent) }
    }

    @Test
    fun `createStudent - error occurred`() {

        // Arrange
        val expectedSectionName = "Stretching"
        val expectedFullName = "Mustafina Elvira"
        val expectedWasPayed = true
        val expectedNumberOfLessons = 9
        val expectedBalanceOfLessons = 9

        val mockRepository = mockk<NewStudentRepository> {
            every { createStudent(any()) } returns Single.error(Exception())
        }

        val mockSectionsListRepository = mockk<SectionsListRepository>()

        val newStudentViewModel = NewStudentViewModel(mockRepository, mockSectionsListRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newStudentViewModel.isLoading.observeForever(mockLoadingObserver)
        newStudentViewModel.error.observeForever(mockErrorObserver)

        // Act
        newStudentViewModel.createStudent(expectedFullName,
            expectedSectionName,
            expectedNumberOfLessons,
            expectedWasPayed,
            expectedBalanceOfLessons)

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verifyOrder {
            mockErrorObserver.onChanged(false)
            mockErrorObserver.onChanged(true)
        }
    }
}