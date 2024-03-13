package com.example.benchmarkapp.controller

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.tan
import java.math.BigInteger
import java.util.Random

class CpuController {
    suspend fun testFloatingPoint () {
        return withContext(Dispatchers.IO) {
            val numberOfAngles = 1000000;
            val angles = DoubleArray(numberOfAngles) {
                Math.random() * 2 * Math.PI;
            }
            val tangentResults = DoubleArray(numberOfAngles)
            for (i in 0 until numberOfAngles) {
                tangentResults[i] = tan(angles[i])
            }
        }
    }
    suspend fun testFloatingPointAverage (runs: Int) : Long {
        return withContext(Dispatchers.IO) {
            var totalTimeElapsed = 0L;
            repeat(runs) {
                val startTime = System.currentTimeMillis()
                testFloatingPoint()
                val endTime = System.currentTimeMillis()
                val totalTime = endTime - startTime
                totalTimeElapsed += totalTime
            }
            Log.d("time1", (totalTimeElapsed/runs).toString())
            totalTimeElapsed/runs
        }
    }

    fun generateLargePrime() : BigInteger {
        val numberOfBits = 2048
        val random = Random()
        return BigInteger.probablePrime(numberOfBits, random)
    }

    fun calculatePhiN(p: BigInteger, q: BigInteger): BigInteger {
        return (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE))
    }

    fun chooseE(phiN: BigInteger) : BigInteger {
        var e = BigInteger("65537")
        while(phiN.gcd(e) != BigInteger.ONE) {
            e = e.add(BigInteger.ONE)
        }
        return e
    }

    fun calculateD(e: BigInteger, phiN: BigInteger) : BigInteger {
        return e.modInverse(phiN)
    }

    fun encrypt(message: BigInteger, e: BigInteger, n: BigInteger) : BigInteger {
        return message.modPow(e, n)
    }

    fun decrypt(ciphertext: BigInteger, d: BigInteger, n: BigInteger) : BigInteger {
        return ciphertext.modPow(d, n)
    }

    suspend fun testInteger() {
        return withContext(Dispatchers.IO) {
            val p = generateLargePrime()
            val q = generateLargePrime()
            val n = p.multiply(q)
            val phiN = calculatePhiN(p, q)
            val e = chooseE(phiN)
            val d = calculateD(e, phiN)
            val message = BigInteger("7919")
            val encryptedMessage = encrypt(message, e, n)
            val decryptedMessage = decrypt(encryptedMessage, d, n)
        }
    }

    suspend fun testIntegerAverage (runs: Int) : Long {
        return withContext(Dispatchers.IO) {
            var totalTimeElapsed = 0L;
            repeat(runs) {
                val startTime = System.currentTimeMillis()
                testInteger()
                val endTime = System.currentTimeMillis()
                val totalTime = endTime - startTime
                totalTimeElapsed += totalTime
            }
            Log.d("time2", (totalTimeElapsed/runs).toString())
            totalTimeElapsed/runs
        }
    }

    suspend fun testOverallCpu (runs: Int) : Long {
        return withContext(Dispatchers.IO) {
            testIntegerAverage(runs) + testFloatingPointAverage(runs)
        }
    }
}