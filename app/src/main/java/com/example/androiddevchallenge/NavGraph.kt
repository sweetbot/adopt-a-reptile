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
import com.example.androiddevchallenge.ui.content.AnimalListContent
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
