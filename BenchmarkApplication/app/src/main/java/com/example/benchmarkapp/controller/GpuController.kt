package com.example.benchmarkapp.controller

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class GpuController {
    /*suspend fun testGpu(): {
        return withContext(Dispatchers.IO) {

        }
    }*/

    suspend fun testGpuAverage(runs: Int) : Long {
        return withContext(Dispatchers.IO) {
            var totalTimeElapsed = 0L;
            repeat(runs) {
                val startTime = System.currentTimeMillis()
                //testGpu()
                val endTime = System.currentTimeMillis()
                val totalTime = endTime - startTime
                totalTimeElapsed += totalTime
            }
            totalTimeElapsed / runs
        }
    }
}