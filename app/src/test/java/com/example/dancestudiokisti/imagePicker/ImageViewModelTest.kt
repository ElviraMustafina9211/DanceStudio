package com.example.dancestudiokisti.imagePicker

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

class ImageViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun `getImageList - images retrieved`() {
        // Arrange
        val expectedImages = listOf(
            Image(
                "https://backendlessappcontent.com/FFE00D00-0065-AC54-FF29-8E6A4D2D4200/1E545F28-B2D8-43E5-9BF3-0E1E350F051D/files/Image/stretcher.png",
            )
        )

        val mockRepository = mockk<ImageRepository> {
            every { getImageList() } returns Single.just(expectedImages)
        }

        val imageViewModel = ImageViewModel(mockRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        imageViewModel.isLoading.observeForever(mockLoadingObserver)
        imageViewModel.error.observeForever(mockErrorObserver)

        // Act
        imageViewModel.getImageList()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verify {
            mockErrorObserver.onChanged(false)
        }
        assert(imageViewModel.imagesLiveData.value == expectedImages)
    }

    @Test
    fun `getImagesList - error occurred`() {
        // Arrange
        val mockRepository = mockk<ImageRepository> {
            every { getImageList() } returns Single.error(Exception())
        }

        val imageViewModel = ImageViewModel(mockRepository)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        imageViewModel.isLoading.observeForever(mockLoadingObserver)
        imageViewModel.error.observeForever(mockErrorObserver)

        // Act
        imageViewModel.getImageList()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verifyOrder {
            mockErrorObserver.onChanged(false)
            mockErrorObserver.onChanged(true)
        }
        assert(imageViewModel.imagesLiveData.value == null)
    }
}