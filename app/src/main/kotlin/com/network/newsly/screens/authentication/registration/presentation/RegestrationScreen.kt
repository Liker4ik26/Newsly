package com.network.newsly.screens.authentication.registration.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.network.newsly.R
import com.network.newsly.core_ui.EmailTextField
import com.network.newsly.core_ui.PasswordTextField
import com.network.newsly.core_ui.TopHeadlineBlock
import com.network.newsly.screens.edit_profile.presentation.components.FirstNameAndLastNameTextField
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun RegistrationScreen(navigation: RegistrationScreenNavigation) {
    RegistrationScreen(
        onNavigateUpClick = navigation::navigateUp,
        onNavigateToHome = navigation::navigationToHomeScreen
    )
}

@Composable
private fun RegistrationScreen(
    onNavigateUpClick: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RegistrationUiEffect.NavigateBack -> onNavigateUpClick()
                is RegistrationUiEffect.NavigateToHomeScreen -> onNavigateToHome()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = { viewModel.sendEvent(RegistrationUiEvent.OnNavigateBack) },
            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = stringResource(id = R.string.back_nav_icon)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            TopHeadlineBlock(text = stringResource(id = R.string.registration))

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                FirstNameAndLastNameTextField(
                    text = state.firstName,
                    onInput = { viewModel.sendEvent(RegistrationUiEvent.OnFirstNameInput(it)) },
                    hint = R.string.first_name,
                    isError = state.isError,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                FirstNameAndLastNameTextField(
                    text = state.lastName,
                    onInput = { viewModel.sendEvent(RegistrationUiEvent.OnLastNameInput(it)) },
                    hint = R.string.last_name,
                    isError = state.isError,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                EmailTextField(
                    email = state.email,
                    image = R.drawable.mail_icon,
                    hint = R.string.email,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    isError = state.isError,
                    onInput = { viewModel.sendEvent(RegistrationUiEvent.OnEmailInput(it)) })

                Spacer(modifier = Modifier.height(16.dp))

                PasswordTextField(
                    password = state.password,
                    image = R.drawable.lock_icon,
                    hint = R.string.password,
                    isError = state.isError,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    onInput = { viewModel.sendEvent(RegistrationUiEvent.OnPasswordInput(it)) })

                Spacer(modifier = Modifier.height(50.dp))

            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                onClick = { viewModel.sendEvent(RegistrationUiEvent.OnNavigateToHomeScreen) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(id = R.string.registration),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}