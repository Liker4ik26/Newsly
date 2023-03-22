package com.network.newsly.screens.search_news.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.network.newsly.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    text: String,
    modifier: Modifier = Modifier,
    onType: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onType(it) },
        modifier = modifier.testTag("search_field"),
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = stringResource(
                    id = R.string.search
                )
            )
        },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}