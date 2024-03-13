package com.example.benchmarkapp.view

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object ResultsScreen : Screen("ResultsScreen")
}
