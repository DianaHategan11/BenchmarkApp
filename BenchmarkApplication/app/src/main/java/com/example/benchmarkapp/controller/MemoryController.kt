package com.example.benchmarkapp.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MemoryController {
    suspend fun testMemory () {
        return withContext(Dispatchers.IO) {
            val sizeOfArray1 = 1000000
            val sizeOfArray2 = 2000000
            val sizeOfArray3 = 3000000
            val array1 = IntArray(sizeOfArray1) {
                Random.nextInt()
            }
            val array2 = IntArray(sizeOfArray2) {
                Random.nextInt()
            }
            val array3 = IntArray(sizeOfArray3) {
                Random.nextInt()
            }
            for(i in 0 until sizeOfArray1) {
                array1[i] *= array1[i];
            }
            for(i in 0 until sizeOfArray2) {
                array2[i] *= array2[i];
            }
            for(i in 0 until sizeOfArray3) {
                array3[i] *= array3[i];
            }
        }
    }
    suspend fun testMemoryAverage(runs: Int) : Long {
        return withContext(Dispatchers.IO) {
            var totalTimeElapsed = 0L;
            repeat(runs) {
                val startTime = System.currentTimeMillis()
                testMemory()
                val endTime = System.currentTimeMillis()
                val totalTime = endTime - startTime
                totalTimeElapsed += totalTime
            }
            totalTimeElapsed/runs
        }
    }
}