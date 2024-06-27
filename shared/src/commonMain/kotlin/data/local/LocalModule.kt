package data.local

import data.local.dao.CourseDao
import data.local.dao.StudentDao
import getDriver
import getSettings
import org.koin.dsl.module
import org.sam.multiplatfrom_base.AppDatabase

val provideDriver = module {
    single { getDriver() }
}

val provideSqlDelight = module {
    single { AppDatabase(get()) }
}

val provideDeviceStorage = module {
    single { getSettings() }
}

val provideDao = module {
    factory { CourseDao(get()) }
    factory { StudentDao(get()) }
}
