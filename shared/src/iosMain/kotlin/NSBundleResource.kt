import platform.Foundation.NSBundle

class NSBundleResource : AppText {
    override fun appName(): String = NSBundle.mainBundle.localizedStringForKey("app_name", value = "", table = null)
    override fun validationFailText(): String = NSBundle.mainBundle.localizedStringForKey("validation_fail_text", value = "", table = null)
    override fun username(): String = NSBundle.mainBundle.localizedStringForKey("username", value = "", table = null)
    override fun password(): String = NSBundle.mainBundle.localizedStringForKey("password", value = "", table = null)
    override fun name(): String = NSBundle.mainBundle.localizedStringForKey("name", value = "", table = null)
    override fun birthday(): String = NSBundle.mainBundle.localizedStringForKey("birthday", value = "", table = null)
    override fun placeHolder(): String = NSBundle.mainBundle.localizedStringForKey("place_holder", value = "", table = null)
    override fun description(): String = NSBundle.mainBundle.localizedStringForKey("description", value = "", table = null)
    override fun birthdayPlaceHolder(): String = NSBundle.mainBundle.localizedStringForKey("birthday_place_holder", value = "", table = null)
    override fun birthdayDescription(): String = NSBundle.mainBundle.localizedStringForKey("birthday_description", value = "", table = null)
    override fun ok(): String = NSBundle.mainBundle.localizedStringForKey("ok", value = "", table = null)
    override fun cancel(): String = NSBundle.mainBundle.localizedStringForKey("cancel", value = "", table = null)
    override fun goToRegister(): String = NSBundle.mainBundle.localizedStringForKey("go_to_register", value = "", table = null)
    override fun backToLogin(): String = NSBundle.mainBundle.localizedStringForKey("back_to_login", value = "", table = null)
    override fun login(): String = NSBundle.mainBundle.localizedStringForKey("login", value = "", table = null)
    override fun register(): String = NSBundle.mainBundle.localizedStringForKey("register", value = "", table = null)
    override fun loginNavTab(): String = NSBundle.mainBundle.localizedStringForKey("login_nav_tab", value = "", table = null)
    override fun registerNavTab(): String = NSBundle.mainBundle.localizedStringForKey("register_nav_tab", value = "", table = null)
    override fun courseNavTab(): String = NSBundle.mainBundle.localizedStringForKey("course_nav_tab", value = "", table = null)
    override fun studentNavTab(): String = NSBundle.mainBundle.localizedStringForKey("student_nav_tab", value = "", table = null)
    override fun errorApiMessage(): String = NSBundle.mainBundle.localizedStringForKey("error_api_message", value = "", table = null)
    override fun totalStudentsTitle(): String = NSBundle.mainBundle.localizedStringForKey("total_students_title", value = "", table = null)
    override fun enrolledStudent(): String = NSBundle.mainBundle.localizedStringForKey("enrolled_student", value = "", table = null)
    override fun registerCourse(): String = NSBundle.mainBundle.localizedStringForKey("register_course", value = "", table = null)
    override fun removeStudent(): String = NSBundle.mainBundle.localizedStringForKey("remove_student", value = "", table = null)
}