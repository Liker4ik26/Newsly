package com.network.newsly.screens.edit_profile.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.network.newsly.core_ui.BodyTextFieldHint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstNameAndLastNameTextField(
    text: String,
    modifier: Modifier = Modifier,
    onInput: (String) -> Unit,
    isError: Boolean,
    hint: Int
) {
    OutlinedTextField(
        value = text, onValueChange = { onInput(it) }, modifier = modifier,
        textStyle = MaterialTheme.typography.bodySmall,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.surface,
            errorContainerColor = MaterialTheme.colorScheme.surface,
            errorIndicatorColor = MaterialTheme.colorScheme.error,
            errorCursorColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.error
        ),
        isError = isError,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(15.dp),
        placeholder = { BodyTextFieldHint(text = hint) },
    )
}