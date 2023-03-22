package com.network.newsly.screens.edit_profile.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.network.newsly.R
import com.network.newsly.screens.edit_profile.presentation.components.FirstNameAndLastNameTextField
import com.network.newsly.core_ui.EditButton
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EditProfileScreen(navigation: EditProfileNavigation) {
    EditProfileScreen(
        onNavigateToProfile = navigation::navigateToProfile,
        onNavigateBack = navigation::navigateBack
    )
}

@Composable
private fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    onNavigateToProfile: () -> Unit,
    onNavigateBack: () -> Unit
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect() { effect ->
            when (effect) {
                is EditProfileUiEffect.NavigateToProfile -> onNavigateToProfile()
                is EditProfileUiEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.sendEvent(EditProfileUiEvent.OnNavigateBack) },
                colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = stringResource(id = R.string.back_nav_icon)
                )
            }
            Text(
                text = stringResource(id = R.string.edit_profile),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        FirstNameAndLastNameTextField(
            text = state.firstName,
            onInput = { viewModel.sendEvent(EditProfileUiEvent.OnFirstNameInput(it)) },
            hint = R.string.first_name,
            isError = state.isError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        FirstNameAndLastNameTextField(
            text = state.lastName,
            onInput = { viewModel.sendEvent(EditProfileUiEvent.OnLastNameInput(it)) },
            hint = R.string.last_name,
            isError = state.isError,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        EditButton(text = R.string.save, modifier = Modifier.fillMaxWidth()) {
            viewModel.sendEvent(EditProfileUiEvent.OnSave)
            if (state.isError) {
                Toast.makeText(context, "Null Field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}