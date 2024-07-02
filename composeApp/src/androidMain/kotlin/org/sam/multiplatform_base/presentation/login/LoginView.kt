package org.sam.multiplatform_base.presentation.login

import AppText
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import framework.base.LoadingState
import kotlinx.coroutines.flow.asStateFlow
import org.koin.compose.koinInject
import org.sam.multiplatform_base.navigation.NavigationProvider
import org.sam.multiplatform_base.app.component.ErrorDialog
import org.sam.multiplatform_base.app.component.ExtraLargeSpacer
import org.sam.multiplatform_base.app.component.FullScreenLoading
import org.sam.multiplatform_base.app.theme.BgColor
import org.sam.multiplatform_base.app.theme.*
import org.sam.multiplatform_base.presentation.login.view.*
import viewModel.login.LoginEvent
import viewModel.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navigationProvider: NavigationProvider) {
    val appText = koinInject<AppText>()
    val viewModel = koinInject<LoginViewModel>()

    val uiState by viewModel.uiState.asStateFlow().collectAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (uiState.isRegisterScreen) appText.registerNavTab() else appText.loginNavTab())
                },
                colors = TopAppBarDefaults.topAppBarColors(BgColor),
            )
        },
        content = { padding ->
            val modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .clickable(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
            Column(modifier = modifier) {
                LoginInputField(
                    modifier = Modifier.padding(top = dimen38()),
                    label = appText.username(),
                    value = uiState.username,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputUsername(newValue)
                        )
                    },
                    placeholder = appText.placeHolder(),
                    helperText = appText.description(),
                )
                LoginInputField(
                    modifier = Modifier.padding(top = dimen20()),
                    label = appText.password(),
                    value = uiState.password,
                    onValueChange = { newValue ->
                        viewModel.onTriggerEvent(
                            LoginEvent.InputPassword(newValue)
                        )
                    },
                    placeholder = appText.placeHolder(),
                    helperText = appText.description(),
                )
                if (uiState.isRegisterScreen) {
                    LoginInputField(
                        modifier = Modifier.padding(top = dimen20()),
                        label = appText.name(),
                        value = uiState.name,
                        onValueChange = { newValue ->
                            viewModel.onTriggerEvent(
                                LoginEvent.InputName(newValue)
                            )
                        },
                        placeholder = appText.placeHolder(),
                        helperText = appText.description(),
                    )
                    LoginBirthButton(uiState) { year, month ->
                        viewModel.onTriggerEvent(LoginEvent.InputBirth(year, month))
                    }
                    ExtraLargeSpacer()
                    LoginButton(appText.register()) {
                        viewModel.onTriggerEvent(LoginEvent.Register)
                    }
                    ExtraLargeSpacer()
                    LoginButton(appText.backToLogin()) {
                        viewModel.onTriggerEvent(LoginEvent.ChangeLoginMode)
                    }

                } else {
                    ExtraLargeSpacer()
                    LoginButton(appText.login()) {
                        viewModel.onTriggerEvent(LoginEvent.Login)
                    }
                    ExtraLargeSpacer()
                    LoginButton(appText.goToRegister()) {
                        viewModel.onTriggerEvent(LoginEvent.ChangeLoginMode)
                    }

                }
            }

            when (val stateObserver = uiState.loadingState) {
                is LoadingState.Loading -> FullScreenLoading()
                is LoadingState.Loaded -> {
                    navigationProvider.openSam()
                }

                is LoadingState.Error -> {
                    ErrorDialog(content = stateObserver.mess) {
                        viewModel.onTriggerEvent(LoginEvent.IdleReturn)
                    }
                }

                else -> {}
            }
        }
    )
}