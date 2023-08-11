package com.branislavbily.rocket.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoBackIconWithTitle(
    onIconPressed: () -> Unit,
    title: String,
) {
    Row(
        modifier = Modifier.clickable {
            onIconPressed()
        },
    ) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Go back arrow",
        )
        Text(text = title)
    }
}
