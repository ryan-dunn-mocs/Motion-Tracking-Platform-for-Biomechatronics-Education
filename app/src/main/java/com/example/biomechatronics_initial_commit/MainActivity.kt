package com.example.biomechatronics_initial_commit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.biomechatronics_initial_commit.ui.theme.Biomechatronics_initial_commitTheme
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Biomechatronics_initial_commitTheme {
                Column(modifier = Modifier.statusBarsPadding()) {
                    Text(
                        text = "Page 2"
                    )
                    ButtonGrid()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Button(onClick = { }) {
        Text(
            text = "Button name: $name",
            modifier = modifier
        )
    }
}

@Composable
fun BodyPartButton(
    label: String,
    modifier: Modifier = Modifier
) {
    var isOn by remember { mutableStateOf(false) }

    Button(
        onClick = { isOn = !isOn },
        modifier = modifier.size(width = 240.dp, height = 100.dp),
        shape = RectangleShape
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = if (isOn) Color.Green else Color.Red,
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = if (isOn) "Connected" else "Disconnected",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ButtonGrid(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.CenterVertically
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BodyPartButton("Left Lower Arm")
            BodyPartButton("Right Lower Arm")
            BodyPartButton("Left Upper Arm")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BodyPartButton("Right Upper Arm")
            BodyPartButton("Left Lower Leg")
            BodyPartButton("Right Lower Leg")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BodyPartButton("Left Upper Leg")
            BodyPartButton("Right Upper Leg")
            BodyPartButton("Chest")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Biomechatronics_initial_commitTheme {
        Greeting("GitHub")
    }
}