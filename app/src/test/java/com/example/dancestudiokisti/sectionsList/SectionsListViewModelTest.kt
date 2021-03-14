package com.example.dancestudiokisti.sectionsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dancestudiokisti.RxImmediateSchedulerRule
import com.example.dancestudiokisti.TokenSaver
import com.example.dancestudiokisti.newSection.Section
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test

class SectionsListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Test
    fun `getSectionsList - sections retrieved`() {
        // Arrange
        val expectedSections = listOf(
            Section(
                "Stretching",
                "65415F0C-FDC2-4EE0-B0DC-CD2CE5959E32",
                "https://backendlessappcontent.com/49B0F014-9A2B-0420-FFBA-A5382B5FDA00/D584F591-91A7-4457-AB0D-E67686CC207A/files/Images/stretcher.png"
            )
        )

        val mockRepository = mockk<SectionsListRepository> {
            every { getSectionsList() } returns Single.just(expectedSections)
        }

        val mockTokenSaver = mockk<TokenSaver>{
 //
        }

        val sectionsListViewModel = SectionsListViewModel(mockRepository, mockTokenSaver)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        sectionsListViewModel.isLoading.observeForever(mockLoadingObserver)
        sectionsListViewModel.error.observeForever(mockErrorObserver)

        // Act
        sectionsListViewModel.getSectionsList()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verify {
            mockErrorObserver.onChanged(false)
        }
        assert(sectionsListViewModel.sectionsList.value == expectedSections)
    }

    @Test
    fun `getSectionsList - error occurred`() {
        // Arrange
        val mockRepository = mockk<SectionsListRepositoryImpl> {
            every { getSectionsList() } returns Single.error(Exception())
        }

        val mockTokenSaver = mockk<TokenSaver>{
//
        }

        val sectionsListViewModel = SectionsListViewModel(mockRepository, mockTokenSaver)
        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
        sectionsListViewModel.isLoading.observeForever(mockLoadingObserver)
        sectionsListViewModel.error.observeForever(mockErrorObserver)

        // Act
        sectionsListViewModel.getSectionsList()

        // Assert
        verifyOrder {
            mockLoadingObserver.onChanged(true)
            mockLoadingObserver.onChanged(false)
        }
        verifyOrder {
            mockErrorObserver.onChanged(false)
            mockErrorObserver.onChanged(true)
        }
        assert(sectionsListViewModel.sectionsList.value == null)
    }
}

//    @Test
//    fun `deleteOneSection - section deleted`() {
//
//         Arrange
//        val sections = listOf(
//            Section(
//                "Stretching",
//                "65415F0C-FDC2-4EE0-B0DC-CD2CE5959E32",
//                "https://backendlessappcontent.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/files/images_unicorns/stretcher.png"
//            ),
//            Section(
//                "Pole Dance",
//                "C4F85F67-F17C-428D-9944-A71015CA6C09",
//                "https://backendlessappcontent.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/files/images_unicorns/pole_dancer.png"
//            ),
//            Section(
//                "Yoga",
//                "C376FBD8-2379-43CD-A688-DB3EEA5577FA",
//                "https://backendlessappcontent.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/files/images_unicorns/section_one.png"
//            )
//        )
//
//        val expectedSections = listOf(
//            Section(
//                "Stretching",
//                "65415F0C-FDC2-4EE0-B0DC-CD2CE5959E32",
//                "https://backendlessappcontent.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/files/images_unicorns/stretcher.png"
//            ),
//            Section(
//                "Pole Dance",
//                "C4F85F67-F17C-428D-9944-A71015CA6C09",
//                "https://backendlessappcontent.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/FCA93BA4-4832-4000-87B1-2A27B27356C4/files/images_unicorns/pole_dancer.png"
//            )
//        )
//
//        val needToDelete = "C376FBD8-2379-43CD-A688-DB3EEA5577FA"
//
//        val mockRepository = mockk<SectionsListRepositoryImpl> {
//            every { deleteOneSection(needToDelete) } returns Completable.complete()
//        }
//
//        val sectionsListViewModel = SectionsListViewModel(mockRepository)
//        val mockLoadingObserver = mockk<Observer<Boolean>>(relaxed = true)
//        val mockErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
//        sectionsListViewModel.isLoading.observeForever(mockLoadingObserver)
//        sectionsListViewModel.error.observeForever(mockErrorObserver)
//
//         Act
//        sectionsListViewModel.deleteOneSection().sections.remove(needToDelete)
//
//         Assert
//        verifyOrder {
//            mockLoadingObserver.onChanged(true)
//            mockLoadingObserver.onChanged(false)
//        }
//        verify {
//            mockErrorObserver.onChanged(false)
//        }
//        assert(sectionsListViewModel.sectionsList.value == expectedSections)
//    }
//}