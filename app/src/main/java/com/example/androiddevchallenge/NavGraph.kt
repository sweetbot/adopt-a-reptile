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
package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.MainDestinations.ANIMAL_DETAIL_ID_KEY
import com.example.androiddevchallenge.ui.content.AnimalDetailContent
import com.example.androiddevchallenge.ui.content.HomeScreenContent

/**
 * Destinations used in the ([AnimalApp]).
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val ANIMAL_DETAIL_ROUTE = "animal"
    const val ANIMAL_DETAIL_ID_KEY = "animalId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.HOME_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            HomeScreenContent(selectAnimal = actions.selectAnimal)
        }
        composable(
            "${MainDestinations.ANIMAL_DETAIL_ROUTE}/{$ANIMAL_DETAIL_ID_KEY}",
            arguments = listOf(navArgument(ANIMAL_DETAIL_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            AnimalDetailContent(
                itemId = arguments.getLong(ANIMAL_DETAIL_ID_KEY),
                selectAnimal = actions.selectAnimal,
                upPress = actions.upPress
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val selectAnimal: (Long) -> Unit = { animalId: Long ->
        navController.navigate("${MainDestinations.ANIMAL_DETAIL_ROUTE}/$animalId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
