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
fun ButtonGrid(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("1", fontSize = 24.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("2", fontSize = 24.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("3", fontSize = 24.sp)
            }
        }

        Row {
            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("4", fontSize = 24.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("5", fontSize = 24.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("6", fontSize = 24.sp)
            }
        }

        Row {
            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("7", fontSize = 24.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("8", fontSize = 24.sp)
            }

            Button(
                onClick = { },
                modifier = Modifier.size(100.dp)
            ) {
                Text("9", fontSize = 24.sp)
            }
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