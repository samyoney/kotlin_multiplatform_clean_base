package data.model.dto

import data.model.local.CourseEntity
import data.model.local.StudentEntity
import org.sam.multiplatfrombase.CourseTable
import org.sam.multiplatfrombase.StudentTable

fun CourseTable.toEntity() = CourseEntity(
    id = id, name = name, instructor = instructor, topics = topics.split(";")
)

fun StudentTable.toEntity() = StudentEntity(
    id = id, courseId = courseId, name = name, birth = birth
)

fun CourseEntity.toDto() = CourseDto(
    id = id, name = name, instructor = instructor, topics = topics
)

fun StudentEntity.toDto() = StudentDto(
    id = id, courseId = courseId, name = name, birth = birth
)


fun List<StudentEntity>.toDtoList() = map {
    StudentDto(
        id = it.id,
        courseId = it.courseId,
        name = it.name,
        birth = it.birth,
    )
}

