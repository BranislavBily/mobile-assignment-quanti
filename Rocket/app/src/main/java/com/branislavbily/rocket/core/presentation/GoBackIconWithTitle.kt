package com.branislavbily.rocket.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.branislavbily.rocket.R
import com.branislavbily.rocket.ui.theme.IOSBlue

@Composable
fun GoBackIconWithTitle(
    modifier: Modifier = Modifier,
    onIconPressed: () -> Unit,
    title: String,
) {
    Row(
        modifier = modifier.clickable {
            onIconPressed()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            modifier = Modifier.scale(1.5F),
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = stringResource(R.string.go_back_arrow),
            tint = IOSBlue,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = IOSBlue,
        )
    }
}

@Preview
@Composable
fun GoBackIconWithTitlePreview() {
    GoBackIconWithTitle(onIconPressed = { /*TODO*/ }, title = "Hello")
}
