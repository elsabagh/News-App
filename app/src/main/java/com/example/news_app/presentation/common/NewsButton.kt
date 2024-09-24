package com.example.news_app.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.news_app.ui.theme.Blue
import com.example.news_app.ui.theme.WhiteGray

@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue,
            contentColor = White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )


    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }
}