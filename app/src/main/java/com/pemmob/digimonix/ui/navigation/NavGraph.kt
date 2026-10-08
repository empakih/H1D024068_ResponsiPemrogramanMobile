package com.pemmob.digimonix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.digimonix.ui.screens.DetailScreen
import com.pemmob.digimonix.ui.screens.HomeScreen
import com.pemmob.digimonix.ui.viewmodel.DetailViewModel
import com.pemmob.digimonix.ui.viewmodel.HomeViewModel

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{digimonId}") {
        fun createRoute(digimonId: Int): String = "detail/$digimonId"
    }
}

@Composable
fun DigimonNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = homeViewModel,
                onDigimonClick = { digimonId ->
                    navController.navigate(Screen.Detail.createRoute(digimonId))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("digimonId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val digimonId = backStackEntry.arguments?.getInt("digimonId") ?: 1
            val detailViewModel: DetailViewModel = viewModel()
            DetailScreen(
                digimonId = digimonId,
                viewModel = detailViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
