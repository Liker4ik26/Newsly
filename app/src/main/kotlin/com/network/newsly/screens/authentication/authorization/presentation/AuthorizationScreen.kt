package com.network.newsly.screens.authentication.authorization.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.network.newsly.R
import com.network.newsly.core_ui.EmailTextField
import com.network.newsly.core_ui.PasswordTextField
import com.network.newsly.core_ui.TopHeadlineBlock
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AuthorizationScreen(navigation: AuthorizationScreenNavigation) {
    AuthorizationScreen(
        onNavigateToRegistrationScreen = navigation::navigateToRegistrationScreen,
        onNavigateToHome = navigation::navigateToHomeScreen
    )
}

@Composable
private fun AuthorizationScreen(
    onNavigateToRegistrationScreen: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AuthorizationEffect.NavigateToRegistrationScreen -> onNavigateToRegistrationScreen()
                is AuthorizationEffect.NavigateToHomeScreen -> onNavigateToHome()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TopHeadlineBlock(text = stringResource(id = R.string.authorization))
        Spacer(modifier = Modifier.height(25.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            EmailTextField(
                email = state.email,
                image = R.drawable.mail_icon,
                hint = R.string.email,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                isError = state.isError,
                onInput = { viewModel.sendEvent(AuthorizationUiEvent.OnEmailInput(it)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                password = state.password,
                image = R.drawable.lock_icon,
                hint = R.string.password,
                isError = state.isError,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                onInput = { viewModel.sendEvent(AuthorizationUiEvent.OnPasswordInput(it)) })
            Spacer(modifier = Modifier.height(50.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            onClick = { viewModel.sendEvent(AuthorizationUiEvent.OnNavigateToRegistrationScreen) },
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            onClick = { viewModel.sendEvent(AuthorizationUiEvent.OnNavigateToHome) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = stringResource(id = R.string.authorization),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}