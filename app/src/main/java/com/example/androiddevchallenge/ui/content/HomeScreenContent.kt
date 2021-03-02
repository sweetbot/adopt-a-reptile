/*
 * Copyright 2020 The Android Open Source Project
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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.animalItems
import com.example.androiddevchallenge.ui.common.EmptyScreen
import com.example.androiddevchallenge.ui.theme.PinkTheme
import com.example.androiddevchallenge.ui.theme.YellowTheme

@Composable
fun HomeScreenContent(selectAnimal: (Long) -> Unit) {
    PinkTheme(darkTheme = isSystemInDarkTheme()) {
        val navType = rememberSaveable { mutableStateOf(NavType.ANIMALS) }
        Scaffold(
            topBar = { YellowTheme { TopBar(navType) } },
            bottomBar = { YellowTheme { BottomBar(navType) } }
        ) {
            HomeContent(navType, selectAnimal)
        }
    }
}

enum class NavType {
    ANIMALS, ME
}

@Composable
fun BottomBar(navType: MutableState<NavType>) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.LightGray,
        elevation = 4.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.CatchingPokemon, contentDescription = null) },
            selected = navType.value == NavType.ANIMALS,
            onClick = { navType.value = NavType.ANIMALS },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = null) },
            selected = navType.value == NavType.ME,
            onClick = { navType.value = NavType.ME },
        )
    }
}

@Composable
fun TopBar(navType: MutableState<NavType>) {
    val title = when (navType.value) {
        NavType.ANIMALS -> "Adopt A Reptile \uD83D\uDC0A \t\uD83E\uDD8E \uD83E\uDD5A \uD83D\uDC0D \uD83D\uDC22 \uD83E\uDD95"
        NavType.ME -> "First Sight ❤️️"
    }
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = {}) {
                Icons.Default.MyLocation
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun HomeContent(navType: MutableState<NavType>, selectAnimal: (Long) -> Unit) {
    when (navType.value) {
        NavType.ANIMALS -> {
            val modifier = Modifier.padding(10.dp)
            AnimalListContent(animalItems = animalItems, selectAnimal = selectAnimal, modifier)
        }
        NavType.ME -> EmptyScreen()
    }
}