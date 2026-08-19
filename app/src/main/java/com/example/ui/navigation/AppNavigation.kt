package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ui.screens.additem.AddItemCaptureScreen
import com.example.ui.screens.additem.AddItemProcessingScreen
import com.example.ui.screens.additem.AddItemReviewScreen
import com.example.ui.screens.archive.ArchiveScreen
import com.example.ui.screens.closet.ClosetScreen
import com.example.ui.screens.hair.HairSetupScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.itemdetail.ItemDetailScreen
import com.example.ui.screens.itemdetail.StyleAnchorVibePickerScreen
import com.example.ui.screens.settings.SettingsScreen
import com.example.ui.viewmodel.TesseraViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: TesseraViewModel,
    modifier: Modifier = Modifier
) {
    var capturedImageUri by remember { mutableStateOf("") }
    var processedName by remember { mutableStateOf("") }
    var processedCategory by remember { mutableStateOf("") }
    var processedVibe by remember { mutableStateOf("") }
    var processedCutoutUrl by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToItemDetail = { itemId ->
                    navController.navigate(Screen.ItemDetail.createRoute(itemId))
                }
            )
        }

        composable(Screen.Closet.route) {
            ClosetScreen(
                viewModel = viewModel,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToItemDetail = { itemId ->
                    navController.navigate(Screen.ItemDetail.createRoute(itemId))
                }
            )
        }

        composable(Screen.Archive.route) {
            ArchiveScreen(
                viewModel = viewModel,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                viewModel = viewModel,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.Home.route) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screen.HairSetup.route) {
            HairSetupScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.AddItemCapture.route) {
            AddItemCaptureScreen(
                onCaptureComplete = { imageUri ->
                    capturedImageUri = imageUri
                    navController.navigate(Screen.AddItemProcessing.route)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.AddItemProcessing.route) {
            AddItemProcessingScreen(
                imageUri = capturedImageUri,
                onProcessingFinished = { name, category, vibe, cutoutUrl ->
                    processedName = name
                    processedCategory = category
                    processedVibe = vibe
                    processedCutoutUrl = cutoutUrl
                    navController.navigate(Screen.AddItemReview.route) {
                        popUpTo(Screen.AddItemCapture.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.AddItemReview.route) {
            AddItemReviewScreen(
                name = processedName,
                category = processedCategory,
                vibe = processedVibe,
                imageUrl = processedCutoutUrl,
                viewModel = viewModel,
                onRetake = {
                    navController.navigate(Screen.AddItemCapture.route) {
                        popUpTo(Screen.AddItemReview.route) { inclusive = true }
                    }
                },
                onSaved = {
                    navController.navigate(Screen.Closet.route) {
                        popUpTo(Screen.Home.route)
                    }
                }
            )
        }

        composable(
            route = Screen.ItemDetail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId") ?: 1L
            ItemDetailScreen(
                itemId = itemId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNavigateToStyleAnchor = { id ->
                    navController.navigate(Screen.StyleAnchorVibePicker.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.StyleAnchorVibePicker.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId") ?: 1L
            StyleAnchorVibePickerScreen(
                itemId = itemId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onUseFit = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
