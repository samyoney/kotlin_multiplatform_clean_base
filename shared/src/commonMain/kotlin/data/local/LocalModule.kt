package data.local

import data.local.dao.CourseDao
import data.local.dao.StudentDao
import org.koin.dsl.module
import org.sam.multiplatfrom_base.AppDatabase

val provideSqlDelight = module {
    single { AppDatabase(get()) }
}

val provideDao = module {
    factory { CourseDao(get()) }
    factory { StudentDao(get()) }
}
