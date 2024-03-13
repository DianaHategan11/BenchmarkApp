package com.example.benchmarkapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.benchmarkapp.view.theme.DiaBlue

@Composable
@Preview(showBackground = true)
fun ResultsScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(DiaBlue),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(50.dp))
        Text("Best performing devices",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold)
    }
}