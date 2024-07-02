package org.sam.multiplatform_base.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import framework.base.LoadingState
import kotlinx.coroutines.flow.asStateFlow
import org.koin.compose.koinInject
import org.sam.multiplatform_base.navigation.NavigationProvider
import org.sam.multiplatform_base.R
import org.sam.multiplatform_base.app.component.ErrorDialog
import org.sam.multiplatform_base.app.theme.BgColor
import viewModel.splash.SplashEvent
import viewModel.splash.SplashViewModel

@Composable
fun SplashView(navigationProvider: NavigationProvider) {
    val viewModel = koinInject<SplashViewModel>()
    val systemUiController = rememberSystemUiController()

    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    SideEffect {
        systemUiController.setStatusBarColor(BgColor)
        systemUiController.setNavigationBarColor(BgColor)
    }

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(SplashEvent.InitData)
    }

    when (val stateObserver = uiState) {
        is LoadingState.Idle -> {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = BgColor
            ) {
                BoxWithConstraints(
                    contentAlignment = Alignment.Center
                ) {
                    val imageSize = maxWidth / 2
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(imageSize)
                    )
                }
            }
        }

        is LoadingState.Loaded -> {
            val isNextLogin = stateObserver.data?.isNextLogin ?: true
            if (isNextLogin) {
                navigationProvider.openLogin()
            } else {
                navigationProvider.openSam()
            }
        }

        is LoadingState.Error ->
            ErrorDialog(
                content = stateObserver.mess
            ) {
            }

        else -> {
        }

    }
}