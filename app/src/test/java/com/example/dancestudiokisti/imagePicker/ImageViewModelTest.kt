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
                "https://backendlessappcontent.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/files/images_unicorns/stretcher.png",
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
            mockErrorObserver.onChanged(true)
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