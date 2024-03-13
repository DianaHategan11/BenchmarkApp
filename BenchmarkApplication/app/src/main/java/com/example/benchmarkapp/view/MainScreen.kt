package com.example.benchmarkapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.benchmarkapp.R
import com.example.benchmarkapp.controller.CpuController
import com.example.benchmarkapp.controller.GpuController
import com.example.benchmarkapp.controller.MemoryController
import com.example.benchmarkapp.view.theme.DiaBlue
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

@Composable
fun MainScreen(cpuController: CpuController, memoryController: MemoryController, navController: NavController) {
    val scope = rememberCoroutineScope()
    var cpuScore by remember {
        mutableStateOf(0L)
    }
    var gpuScore by remember {
        mutableStateOf(0L)
    }
    var memoryScore by remember {
        mutableStateOf(0L)
    }
    var started by remember {
        mutableStateOf(false)
    }
    var overallScore by remember {
        mutableStateOf(0L)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(DiaBlue)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(100.dp))
        Button(onClick = {
            scope.launch {
                started = true
                val cpuResult = async {
                    cpuController.testOverallCpu(5)
                }
                val memoryResult = async {
                    memoryController.testMemoryAverage(5)
                }
                cpuScore = cpuResult.await()
                memoryScore = memoryResult.await()
                overallScore += (0.3 * memoryScore).toLong();
                overallScore += (0.5 * cpuScore).toLong();
            }
        },
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Test",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = "performance",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.height(50.dp))
        /*Button(onClick = {
                         navController.navigate(Screen.ResultsScreen.route)
        },
            modifier = Modifier.size(170.dp, 50.dp)) {
            Text(text = "View rankings",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(50.dp))*/
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.width(170.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Your score: ",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
                Text(text = overallScore.toString(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
            Divider()
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "CPU: ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
                Text(text = cpuScore.toString(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
            Divider()
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "GPU: ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
                Text(text = gpuScore.toString(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
            Divider()
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Memory: ",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
                Text(text = memoryScore.toString(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
            Divider()
        }
        if (started) {
            val startTime = System.currentTimeMillis()
            for (i in 0 until 10000){
                Image(
                    painterResource(id = R.drawable.android),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .aspectRatio(1f)
                        .clip(RectangleShape)
                )
            }
            val endTime = System.currentTimeMillis()
            val totalTime = endTime - startTime
            LaunchedEffect(key1 = true){
                gpuScore = totalTime
                overallScore += (0.2 * gpuScore).toLong();
            }
        }
    }
}