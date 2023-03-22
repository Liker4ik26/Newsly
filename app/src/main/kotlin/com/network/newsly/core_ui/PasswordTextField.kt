package com.network.newsly.core_ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    modifier: Modifier = Modifier,
    image: Int,
    hint: Int,
    isError: Boolean,
    onInput: (String) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = { onInput(it) },
        modifier = modifier,
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
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Password
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = hint),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    )
}