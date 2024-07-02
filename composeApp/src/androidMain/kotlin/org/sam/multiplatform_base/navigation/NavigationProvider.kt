package org.sam.multiplatform_base.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.sam.multiplatform_base.presentation.login.LoginView
import org.sam.multiplatform_base.presentation.sam.SamView
import org.sam.multiplatform_base.presentation.splash.SplashView

interface NavigationProvider {
    fun NavGraphBuilder.createNavGraph()
    fun getLaunchScreen(): String
    fun openLogin()
    fun openSam()
    fun navigateUp()
}

class NavigationProviderImpl(private var navController: NavHostController) :
    NavigationProvider {

    private enum class Screen {
        Splash,
        Login,
        Sam
    }

    override fun NavGraphBuilder.createNavGraph() {
        composable(Screen.Splash.name) { SplashView(this@NavigationProviderImpl) }
        composable(Screen.Login.name) { LoginView(this@NavigationProviderImpl) }
        composable(Screen.Sam.name) { SamView() }
    }

    override fun getLaunchScreen() = Screen.Splash.name

    override fun openLogin() {
        navController.popBackStack()
        navController.navigate(Screen.Login.name)
    }

    override fun openSam() {
        navController.popBackStack()
        navController.navigate(Screen.Sam.name)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}