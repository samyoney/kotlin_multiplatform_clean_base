package data.model.dto

import org.sam.multiplatfrombase.CourseEntity
import org.sam.multiplatfrombase.StudentEntity

fun CourseEntity.toDto() = CourseDto(
    id = id, name = name, instructor = instructor, topics = topics.split(";")
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

