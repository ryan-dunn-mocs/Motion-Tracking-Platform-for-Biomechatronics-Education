package com.example.biomechatronics_initial_commit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.biomechatronics_initial_commit.ui.theme.Biomechatronics_initial_commitTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Biomechatronics_initial_commitTheme {
                CollectDataScreen(
                    onBack = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun CollectDataScreen(
    onBack: () -> Unit
) {

    // -----------------------------
    // RECORDING STATE
    // -----------------------------

    var isRecording by remember {
        mutableStateOf(false)
    }

    // Timer value in seconds
    var elapsedSeconds by remember {
        mutableStateOf(0)
    }

    // -----------------------------
    // TIMER
    // -----------------------------

    LaunchedEffect(isRecording) {

        while (isRecording) {

            delay(1000)

            elapsedSeconds++
        }
    }

    // Convert seconds into MM:SS
    val minutes = elapsedSeconds / 60
    val seconds = elapsedSeconds % 60

    val timerText = String.format(
        "%02d:%02d",
        minutes,
        seconds
    )

    // -----------------------------
    // SCREEN
    // -----------------------------

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // BACK BUTTON
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            Button(
                onClick = {
                    onBack()
                }
            ) {
                Text("← BACK")
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // TITLE
        Text(
            text = "Collect Data",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        // DESCRIPTION
        Text(
            text = "All 9 sensors are connected. Press Begin to start collecting motion data from every sensor at once.",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // -----------------------------
        // SENSOR ROW 1
        // -----------------------------

        SensorRow(
            "LEFT LOWER ARM",
            "RIGHT LOWER ARM",
            "LEFT UPPER ARM"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // -----------------------------
        // SENSOR ROW 2
        // -----------------------------

        SensorRow(
            "RIGHT UPPER ARM",
            "LEFT UPPER LEG",
            "RIGHT LOWER LEG"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // -----------------------------
        // SENSOR ROW 3
        // -----------------------------

        SensorRow(
            "LEFT UPPER LEG",
            "RIGHT UPPER LEG",
            "CHEST"
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // SENSOR COUNT
        Text(
            text = "9 of 9 sensors connected",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // -----------------------------
        // STATUS + TIMER
        // -----------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "● Bluetooth enabled",
                modifier = Modifier.weight(1f)
            )

            Text(
                text = if (isRecording)
                    "● Recording"
                else
                    "● Recording: not started",
                modifier = Modifier.weight(1.2f)
            )

            Text(
                text = timerText
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // -----------------------------
        // START / STOP BUTTON
        // -----------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Button(
                onClick = {

                    if (!isRecording) {

                        // START RECORDING
                        isRecording = true
                        elapsedSeconds = 0

                    } else {

                        // STOP RECORDING
                        isRecording = false
                    }
                }
            ) {

                Text(
                    text = if (isRecording)
                        "STOP DATA COLLECTION AND VISUALIZE"
                    else
                        "START DATA COLLECTION"
                )
            }
        }
    }
}


// --------------------------------------------------
// SENSOR BOX
// --------------------------------------------------

@Composable
fun SensorRow(
    sensor1: String,
    sensor2: String,
    sensor3: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        SensorBox(sensor1)

        SensorBox(sensor2)

        SensorBox(sensor3)
    }
}


// --------------------------------------------------
// INDIVIDUAL SENSOR
// --------------------------------------------------

@Composable
fun SensorBox(
    sensorName: String
) {

    Column(
        modifier = Modifier
            .width(110.dp)
            .height(70.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface
            )
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = sensorName,
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            text = "● Connected",
            style = MaterialTheme.typography.labelSmall
        )
    }
}
