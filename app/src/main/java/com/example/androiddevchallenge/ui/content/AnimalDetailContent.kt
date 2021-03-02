package com.example.androiddevchallenge.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation.Vertical
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.AnimalItem
import com.example.androiddevchallenge.data.AnimalItemRepository
import com.example.androiddevchallenge.data.animalItems
import com.example.androiddevchallenge.ui.common.AnimalListItem
import com.example.androiddevchallenge.ui.theme.PinkTheme
import com.example.androiddevchallenge.utils.NetworkImage
import com.example.androiddevchallenge.utils.backHandler
import com.example.androiddevchallenge.utils.scrim
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

private val FabSize = 56.dp

@Composable
fun AnimalDetailContent(
    itemId: Long,
    selectAnimal: (Long) -> Unit,
    upPress: () -> Unit
) {
    // Simplified for the sample
    val animal = remember(itemId) { AnimalItemRepository.getItem(itemId) }
    // TODO: Show error if animal not found.
    ItemDetail(animal, selectAnimal, upPress)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemDetail(
    item: AnimalItem,
    selectAnimal: (Long) -> Unit,
    upPress: () -> Unit
) {
    PinkTheme {
        BoxWithConstraints{
            val sheetState = rememberSwipeableState(SheetState.Closed)
            val fabSize = with(LocalDensity.current) { FabSize.toPx() }
            val dragRange = constraints.maxHeight - fabSize
            val scope = rememberCoroutineScope()

            backHandler(
                enabled = sheetState.currentValue == SheetState.Open,
                onBack = {
                    scope.launch {
                        sheetState.animateTo(SheetState.Closed)
                    }
                }
            )

            Box(
                // The Lessons sheet is initially closed and appears as a FAB. Make it openable by
                // swiping or clicking the FAB.
                Modifier.swipeable(
                    state = sheetState,
                    anchors = mapOf(
                        0f to SheetState.Closed,
                        -dragRange to SheetState.Open
                    ),
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Vertical
                )
            ) {
                val openFraction = if (sheetState.offset.value.isNaN()) {
                    0f
                } else {
                    -sheetState.offset.value / dragRange
                }.coerceIn(0f, 1f)
                ItemDescription(item, selectAnimal, upPress)
            }
        }
    }
}

@Composable
private fun ItemDescription(
    item: AnimalItem,
    selectAnimal: (Long) -> Unit,
    upPress: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            item { ItemDescriptionHeader(item, upPress) }
            item { AnimalDescriptionBody(item) }
            item { RelatedItemsContent(item.id, selectAnimal) }
        }
    }
}

@Composable
private fun ItemDescriptionHeader(
    item: AnimalItem,
    upPress: () -> Unit
) {
    Box {
        NetworkImage(
            url = item.thumbUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .scrim(colors = listOf(Color(0x80000000), Color(0x33000000)))
                .aspectRatio(4f / 3f)
        )
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = Color.White, // always white as image has dark scrim
            modifier = Modifier.statusBarsPadding()
        ) {
            IconButton(onClick = upPress) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.label_back)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        OutlinedRoundedAvatar(
            url = item.owner,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 20.dp) // overlap bottom of image
        )
    }
}

@Composable
private fun AnimalDescriptionBody(item: AnimalItem) {
    Text(
        text = item.category.toUpperCase(),
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 36.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    )
    Text(
        text = item.name,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = item.thumbContentDesc,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    Divider(modifier = Modifier.padding(16.dp))
    Text(
        text = stringResource(id = R.string.contact),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

        val annotatedString = buildAnnotatedString {
            append(item.email)
            addStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.brown_200),
                    textDecoration = TextDecoration.Underline
                ),
                start = 0,
                end = item.email.length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = item.email,
                start = 0,
                end = item.email.length
            )

        }

        Text(
            text = annotatedString,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp
                )
        )
    }
}

@Composable
private fun RelatedItemsContent(
    itemId: Long,
    selectAnimal: (Long) -> Unit
) {
    val relatedItems = remember(itemId) { AnimalItemRepository.getRelated(itemId) }
    PinkTheme {
        Surface(
            color = MaterialTheme.colors.primarySurface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.navigationBarsPadding()) {
                Text(
                    text = stringResource(id = R.string.you_ll_also_like),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 24.dp
                        )
                )
                LazyRow(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        bottom = 32.dp,
                        end = FabSize + 8.dp
                    )
                ) {
                    items(relatedItems) { related ->
                        AnimalListItem(
                            item = related,
                            onClick = { selectAnimal(related.id) },
                            titleStyle = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(288.dp, 80.dp),
                            iconSize = 14.dp
                        )
                    }
                }
            }
        }
    }
}

private enum class SheetState { Open, Closed }

private val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0

@Preview(name = "Animal Details")
@Composable
private fun ItemDetailPreview() {
    val itemId = animalItems.first().id
    AnimalDetailContent(
        itemId = itemId,
        selectAnimal = { },
        upPress = { }
    )
}


@Preview(name = "Related")
@Composable
private fun RelatedItemsPreview() {
    val related = animalItems.random()
    RelatedItemsContent(
        itemId = related.id,
        selectAnimal = { },
    )
}
