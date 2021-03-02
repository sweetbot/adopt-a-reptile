/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.data.AnimalItem
import com.example.androiddevchallenge.data.animalItems
import com.example.androiddevchallenge.ui.theme.BlueTheme
import com.example.androiddevchallenge.ui.theme.MainTheme
import com.example.androiddevchallenge.utils.NetworkImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlin.math.ceil

@Composable
fun AnimalListContent(
    animalItems: List<AnimalItem>,
    selectAnimal: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            animalItems.forEach { animal ->
                AnimalContent(animal, selectAnimal)
            }
        }
    }
}

@Composable
fun AnimalContent(
    animal: AnimalItem,
    selectAnimal: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = MainTheme.elevations.card,
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout(
            modifier = Modifier
                .clickable(
                    onClick = { selectAnimal(animal.id) }
                )
                .semantics {
                    contentDescription = "Animals"
                }
        ) {
            val (image, avatar, category, name, likes, icon) = createRefs()
            NetworkImage(
                url = animal.thumbUrl,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(4f / 3f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )
            val outlineColor = LocalElevationOverlay.current?.apply(
                color = MaterialTheme.colors.surface,
                elevation = MainTheme.elevations.card
            ) ?: MaterialTheme.colors.surface
            OutlinedRoundedAvatar(
                url = animal.owner,
                outlineColor = outlineColor,
                modifier = Modifier
                    .size(38.dp)
                    .constrainAs(avatar) {
                        centerHorizontallyTo(parent)
                        centerAround(image.bottom)
                    }
            )
            Text(
                text = animal.category.toUpperCase(),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(16.dp)
                    .constrainAs(category) {
                        centerHorizontallyTo(parent)
                        top.linkTo(avatar.bottom)
                    }
            )
            Text(
                text = animal.name,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(category.bottom)
                    }
            )
            val center = createGuidelineFromStart(0.5f)
            Icon(
                imageVector = Icons.Rounded.Favorite,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .constrainAs(icon) {
                        end.linkTo(center)
                        centerVerticallyTo(likes)
                    }
            )
            Text(
                text = animal.likes.toString(),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(
                        start = 4.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                    .constrainAs(likes) {
                        start.linkTo(center)
                        top.linkTo(name.bottom)
                    }
            )
        }
    }
}

@Composable
fun StaggeredVerticalGrid(
    modifier: Modifier = Modifier,
    maxColumnWidth: Dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        check(constraints.hasBoundedWidth) {
            "Unbounded width not supported"
        }
        val columns = ceil(constraints.maxWidth / maxColumnWidth.toPx()).toInt()
        val columnWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(maxWidth = columnWidth)
        val colHeights = IntArray(columns) { 0 } // track each column's height
        val placeables = measurables.map { measurable ->
            val column = shortestColumn(colHeights)
            val placeable = measurable.measure(itemConstraints)
            colHeights[column] += placeable.height
            placeable
        }

        val height = colHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
            ?: constraints.minHeight
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            val colY = IntArray(columns) { 0 }
            placeables.forEach { placeable ->
                val column = shortestColumn(colY)
                placeable.place(
                    x = columnWidth * column,
                    y = colY[column]
                )
                colY[column] += placeable.height
            }
        }
    }
}

private fun shortestColumn(colHeights: IntArray): Int {
    var minHeight = Int.MAX_VALUE
    var column = 0
    colHeights.forEachIndexed { index, height ->
        if (height < minHeight) {
            minHeight = height
            column = index
        }
    }
    return column
}

@Preview(name = "Animals Portrait")
@Composable
private fun AnimalsPreview() {
    BlueTheme {
        AnimalListContent(
            animalItems = animalItems,
            selectAnimal = { }
        )
    }
}

@Preview(name = "Animals Dark")
@Composable
private fun AnimalsPreviewDark() {
    BlueTheme(darkTheme = true) {
        AnimalListContent(
            animalItems = animalItems,
            selectAnimal = { }
        )
    }
}

@Preview(
    name = "Animals Landscape",
    widthDp = 640,
    heightDp = 360
)
@Composable
private fun AnimalsPreviewLandscape() {
    BlueTheme {
        AnimalListContent(
            animalItems = animalItems,
            selectAnimal = { }
        )
    }
}
