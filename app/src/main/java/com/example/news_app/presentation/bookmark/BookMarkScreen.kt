package com.example.news_app.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.news_app.R
import com.example.news_app.presentation.Dimens.MediumPadding
import com.example.news_app.presentation.common.ArticlesList
import com.example.news_app.presentation.navgraph.Route

@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigate: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPadding,
                start = MediumPadding,
                end = MediumPadding
            )
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        ArticlesList(
            articles = state.article,
            onClick = {
                navigate(Route.DetailsScreen.route)
            }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun BookMarkScreenPreview() {
    BookMarkScreen(
        state = BookMarkState(),
        navigate = {}
    )
}