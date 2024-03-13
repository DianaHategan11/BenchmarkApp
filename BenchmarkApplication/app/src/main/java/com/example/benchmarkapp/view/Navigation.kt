package com.example.benchmarkapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.benchmarkapp.controller.CpuController
import com.example.benchmarkapp.controller.GpuController
import com.example.benchmarkapp.controller.MemoryController

@Composable
fun Navigation(navHostController : NavHostController,
               cpuController: CpuController,
               memoryController: MemoryController) {
    NavHost(navController = navHostController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(cpuController, memoryController , navHostController)
        }
        composable(Screen.ResultsScreen.route) {
            ResultsScreen()
        }
    }
}