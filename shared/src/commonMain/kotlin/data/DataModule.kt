package data

import data.repository.*
import data.usecase.enroll.*
import data.usecase.login.*
import org.koin.dsl.module

val provideRepository = module {
    factory { AccountRepository(get(), get(), get()) }
    factory { CourseRepository(get(), get()) }
    factory { StudentRepository(get(), get()) }
}

val provideUseCase = module {
    factory { AddStudentIntoCourseUseCase(get()) }
    factory { CheckDataInitializedUseCase(get()) }
    factory { FetchCoursesUseCase(get()) }
    factory { FetchStudentsUseCase(get()) }
    factory { GetCoursesUseCase(get()) }
    factory { GetStudentsByCourseIdUseCase(get()) }
    factory { GetStudentsUseCase(get()) }
    factory { RemoveStudentFromCourseUseCase(get()) }
    factory { SaveCoursesUseCase(get()) }
    factory { SaveStudentsUseCase(get()) }

    factory { CheckLoggedInUseCase(get()) }
    factory { FetchAutoLoginUseCase(get()) }
    factory { FetchLoginUseCase(get()) }
    factory { FetchRegisterUseCase(get()) }
    factory { SaveAccountInfoUseCase(get()) }
}