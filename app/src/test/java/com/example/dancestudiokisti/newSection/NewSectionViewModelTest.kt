package com.example.dancestudiokisti.newSection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dancestudiokisti.RxImmediateSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class NewSectionViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun createSection() {

        // Arrange
        val expectedSectionName = "Stretching"
        val expectedImageLink =
            "https://backendlessappcontent.com/BB0CA8EB-8AD2-4723-FF91-7793F1AA1500/610916AA-8659-4808-97EC-E64E03666C98/files/Images/stretcher.png"

        val expectedNewSection = Section(expectedSectionName, "", expectedImageLink)

        val mockNewSectionRepository = mockk<NewSectionRepository> {
            every { createSection(expectedNewSection) } returns Single.just(expectedNewSection)
        }

        val newSectionViewModel = NewSectionViewModel(mockNewSectionRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockCloseScreenObserver = mockk<Observer<Boolean>>(relaxed = true)
        newSectionViewModel.isLoading.observeForever(mockLoadingObserver)
        newSectionViewModel.closeScreen.observeForever(mockCloseScreenObserver)

        // Act
        newSectionViewModel.createSection(expectedSectionName, expectedImageLink)

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
            mockCloseScreenObserver.onChanged(true)
        }
        verify { mockNewSectionRepository.createSection(expectedNewSection) }
    }

    @Test
    fun `createSection - if imageLink == null `() {

        //Arrange
        val mockNewSectionRepository = mockk<NewSectionRepository> {
            every { createSection(any()) } returns Single.error(Exception())
        }

        val newSectionViewModel = NewSectionViewModel(mockNewSectionRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newSectionViewModel.isLoading.observeForever(mockLoadingObserver)
        newSectionViewModel.error.observeForever(mockErrorObserver)

        // Act
        newSectionViewModel.createSection("", null)

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockErrorObserver.onChanged(false)
            mockErrorObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
    }

    @Test
    fun `createSection - error occurred`() {

        //Arrange

        val expectedSectionName = "Stretching"
        val expectedImageLink =
            "https://backendlessappcontent.com/BB0CA8EB-8AD2-4723-FF91-7793F1AA1500/610916AA-8659-4808-97EC-E64E03666C98/files/Images/stretcher.png"

        val expectedNewSection = Section(expectedSectionName, "", expectedImageLink)

        val mockNewSectionRepository = mockk<NewSectionRepository> {
            every { createSection(any()) } returns Single.error(Exception())
        }

        val newSectionViewModel = NewSectionViewModel(mockNewSectionRepository)
        val mockChooseImageErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        newSectionViewModel.chooseImageError.observeForever(mockChooseImageErrorObserver)

        // Act
        newSectionViewModel.createSection(expectedNewSection)

        // Assert
        verifyOrder {
            mockChooseImageErrorObserver.onChanged(true)
        }
    }
}