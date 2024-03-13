package com.example.benchmarkapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.benchmarkapp.controller.CpuController
import com.example.benchmarkapp.controller.GpuController
import com.example.benchmarkapp.controller.MemoryController
import com.example.benchmarkapp.view.theme.BenchmarkAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BenchmarkAppTheme {
                val cpuController = CpuController()
                val memoryController = MemoryController()
                val navController = rememberNavController()
                Navigation(navHostController = navController, cpuController, memoryController)
            }
        }
    }
}