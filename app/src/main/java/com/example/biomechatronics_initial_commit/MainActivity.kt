package com.example.biomechatronics_initial_commit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.biomechatronics_initial_commit.ui.theme.Biomechatronics_initial_commitTheme
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api

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
    Column(modifier) {
        Row {
            Button(onClick = { }) {
                Text("1")
            }
            Button(onClick = { }) {
                Text("2")
            }
            Button(onClick = { }) {
                Text("3")
            }
        }
        Row {
            Button(onClick = { }) {
                Text("4")
            }
            Button(onClick = { }) {
                Text("5")
            }
            Button(onClick = { }) {
                Text("6")
            }
        }
        Row {
            Button(onClick = { }) {
                Text("7")
            }
            Button(onClick = { }) {
                Text("8")
            }
            Button(onClick = { }) {
                Text("9")
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