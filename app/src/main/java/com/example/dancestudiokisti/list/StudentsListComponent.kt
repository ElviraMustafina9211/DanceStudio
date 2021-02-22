package com.example.dancestudiokisti.list

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [StudentsListModule::class])
interface StudentsListComponent {
 fun inject (studentsListFragment: StudentsListFragment)
}