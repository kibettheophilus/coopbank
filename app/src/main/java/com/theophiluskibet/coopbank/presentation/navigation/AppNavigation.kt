package com.theophiluskibet.coopbank.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.theophiluskibet.coopbank.presentation.screens.allcards.AllCardsScreen
import com.theophiluskibet.coopbank.presentation.screens.details.CardDetailScreen
import com.theophiluskibet.coopbank.presentation.screens.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = AllCards,
    ) {
        composable<AllCards> {
            AllCardsScreen()
        }

        composable<CardDetails> { backStackEntry ->
            val details: CardDetails = backStackEntry.toRoute()
            CardDetailScreen()
        }

        composable<Profile> {
            ProfileScreen()
        }
    }
}