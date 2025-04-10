package com.example.healthapp.ui.screens.sport

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import com.example.healthapp.data.SportEntity
import com.example.healthapp.viewmodel.HealthViewModel

@Composable
fun SportScreen(viewModel: HealthViewModel) {
    val sports by viewModel.sports.collectAsState()
    LaunchedEffect(Unit) { viewModel.initializeSport() }

    var showDialog by remember { mutableStateOf(false) }
    var inputSportName by remember { mutableStateOf("") }
    var inputHeartRate by remember { mutableStateOf("") }
    var inputCalories by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { showDialog = true }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Add an exercise")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(sports) { sport ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("sport event：${sport.sportName}", style = MaterialTheme.typography.titleMedium)
                        Text("average heart rate\n：${sport.avgHeartRate} beats/minute")
                        Text("heat consumption\n：${sport.caloriesBurned} kilocalorie")

                        Spacer(modifier = Modifier.height(4.dp))

                        Button(onClick = {
                            viewModel.deleteSport(sport)
                        }, modifier = Modifier.align(Alignment.End)) {
                            Text("Delete Sport")
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add an exercise") },
            text = {
                Column {
                    OutlinedTextField(
                        value = inputSportName,
                        onValueChange = { inputSportName = it },
                        label = { Text("sport event") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = inputHeartRate,
                        onValueChange = { inputHeartRate = it },
                        label = { Text("average heart rate\n") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = inputCalories,
                        onValueChange = { inputCalories = it },
                        label = { Text("heat consumption") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (inputSportName.isNotBlank() && inputHeartRate.isNotBlank() && inputCalories.isNotBlank()) {
                        viewModel.addSport(inputSportName, inputHeartRate.toIntOrNull() ?: 0, inputCalories.toIntOrNull() ?: 0)
                        showDialog = false
                        inputSportName = ""
                        inputHeartRate = ""
                        inputCalories = ""
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
