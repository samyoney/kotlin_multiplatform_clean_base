package org.sam.multiplatform_base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sam.multiplatform_base.app.controller.SetupSystemUi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import framework.pref.DataStoreManager
import initKoin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.koinInject
import org.sam.multiplatform_base.app.theme.AppTheme
import org.sam.multiplatform_base.app.theme.BaseColor
import org.sam.multiplatform_base.app.theme.HeaderColor
import org.sam.multiplatform_base.navigation.NavigationProviderImpl
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        initKoin {
            androidLogger()
            androidContext(applicationContext)
        }
        super.onCreate(savedInstanceState)
        setContent {
            MainView {
                forceExitApp()
            }
        }
    }

    private fun forceExitApp() = CoroutineScope(Dispatchers.IO).launch {
        delay(200)
        finishAndRemoveTask()
        exitProcess(0)
    }
}

@Composable
private fun MainView(onBack: () -> Unit) {
    koinInject<DataStoreManager>()
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val navigationProvider = remember { NavigationProviderImpl(navController) }

    BackHandler { onBack() }

    val interactionSource = remember { MutableInteractionSource() }

    val keyboardController = LocalSoftwareKeyboardController.current
    val dismissKeyboardModifier = Modifier
        .fillMaxSize()
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            keyboardController?.hide()
        }

    AppTheme {
        SetupSystemUi(systemUiController, HeaderColor)

        Surface(
            modifier = dismissKeyboardModifier,
            color = BaseColor
        ) {
            NavHost(navController = navController, startDestination = navigationProvider.getLaunchScreen()) {
                navigationProvider.apply {
                    createNavGraph()
                }
            }
        }
    }
}
