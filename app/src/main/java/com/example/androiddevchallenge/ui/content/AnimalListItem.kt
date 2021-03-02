package com.example.androiddevchallenge.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.AnimalItem
import com.example.androiddevchallenge.data.animalItems
import com.example.androiddevchallenge.ui.theme.MainTheme
import com.example.androiddevchallenge.ui.theme.PinkTheme
import com.example.androiddevchallenge.utils.NetworkImage

@Composable
fun AnimalListItem(
    item: AnimalItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    elevation: Dp = MainTheme.elevations.card,
    titleStyle: TextStyle = MaterialTheme.typography.subtitle1,
    iconSize: Dp = 16.dp
) {
    Surface(
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        Row(modifier = Modifier.clickable(onClick = onClick)) {
            NetworkImage(
                url = item.thumbUrl,
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f)
            )
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = item.name,
                    style = titleStyle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 4.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null,
                        modifier = Modifier.size(iconSize)
                    )
                    Text(
                        text = item.likes.toString(),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                    )
                    Text(
                        text = item.category,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                    )
                    NetworkImage(
                        url = item.owner,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}

@Preview(name = "Animal list item")
@Composable
private fun ListItemPreviewLight() {
    ListItemPreview(false)
}

@Preview(name = "list item – Dark")
@Composable
private fun ListItemPreviewDark() {
    ListItemPreview(true)
}

@Composable
private fun ListItemPreview(darkTheme: Boolean) {
    PinkTheme(darkTheme) {
        AnimalListItem(
            item = animalItems.first(),
            onClick = {}
        )
    }
}
