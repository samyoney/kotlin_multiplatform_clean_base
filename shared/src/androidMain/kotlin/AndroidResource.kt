import android.content.Context
import org.sam.org.shared.R

class AndroidResource(private val context: Context): AppText {
    override fun appName(): String = context.getString(R.string.app_name)
    override fun validationFailText(): String = context.getString(R.string.validation_fail_text)
    override fun username(): String = context.getString(R.string.username)
    override fun password(): String = context.getString(R.string.password)
    override fun name(): String = context.getString(R.string.name)
    override fun birthday(): String = context.getString(R.string.birthday)
    override fun placeHolder(): String = context.getString(R.string.place_holder)
    override fun description(): String = context.getString(R.string.description)
    override fun birthdayPlaceHolder(): String = context.getString(R.string.birthday_place_holder)
    override fun birthdayDescription(): String = context.getString(R.string.birthday_description)
    override fun ok(): String = context.getString(R.string.ok)
    override fun cancel(): String = context.getString(R.string.cancel)
    override fun goToRegister(): String = context.getString(R.string.go_to_register)
    override fun backToLogin(): String = context.getString(R.string.back_to_login)
    override fun login(): String = context.getString(R.string.login)
    override fun register(): String = context.getString(R.string.register)
    override fun loginNavTab(): String = context.getString(R.string.login_nav_tab)
    override fun registerNavTab(): String = context.getString(R.string.register_nav_tab)
    override fun courseNavTab(): String = context.getString(R.string.course_nav_tab)
    override fun studentNavTab(): String = context.getString(R.string.student_nav_tab)
    override fun errorApiMessage(): String = context.getString(R.string.error_api_message)
    override fun totalStudentsTitle(): String = context.getString(R.string.total_students_title)
    override fun enrolledStudent(): String = context.getString(R.string.enrolled_student)
    override fun registerCourse(): String = context.getString(R.string.register_course)
    override fun removeStudent(): String = context.getString(R.string.remove_student)
}