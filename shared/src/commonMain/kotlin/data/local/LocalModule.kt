package data.local

import data.local.dao.CourseDao
import data.local.dao.StudentDao
import org.koin.dsl.module

fun provideDao() = module {
    factory { CourseDao(get()) }
    factory { StudentDao(get()) }
}
