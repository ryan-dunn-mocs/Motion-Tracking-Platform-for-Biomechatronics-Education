package com.example.biomechatronics_initial_commit

importandroid.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biomechatronics_initial_commit.ui.theme.Biomechatronics_initial_commitTheme


/*
 * MainActivity is the starting point of the Android application.
 *
 * The application currently contains four screens:
 *
 * 1. Landing Page
 * 2. Connect and Configure Sensors
 * 3. Collect Data
 * 4. Data Visualization
 *
 * The three functional screens are currently placeholders.
 * They can later be connected to the sensor, data collection,
 * and visualization functionality developed by the team.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // Apply the theme that was automatically created
            // with the Android Studio project.
            Biomechatronics_initial_commitTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    /*
                     * This variable keeps track of which page the
                     * user is currently viewing.
                     *
                     * The application starts on the landing page.
                     */
                    var currentPage by remember {
                        mutableStateOf("landing")
                    }

                    /*
                     * Display a different screen depending on
                     * the value stored in currentPage.
                     */
                    when (currentPage) {

                        "landing" -> LandingPage(
                            onSensorsClick = {
                                currentPage = "sensors"
                            },

                            onCollectClick = {
                                currentPage = "collect"
                            },

                            onVisualizationClick = {
                                currentPage = "visualization"
                            }
                        )

                        "sensors" -> SensorConfigurationPage(
                            onBackClick = {
                                currentPage = "landing"
                            }
                        )

                        "collect" -> CollectDataPage(
                            onBackClick = {
                                currentPage = "landing"
                            }
                        )

                        "visualization" -> DataVisualizationPage(
                            onBackClick = {
                                currentPage = "landing"
                            }
                        )
                    }
                }
            }
        }
    }
}


/*
 * LANDING PAGE
 *
 * This is the first page displayed when the application starts.
 *
 * It gives the user access to the three major sections of the
 * Motion Tracking Platform.
 */
@Composable
fun LandingPage(
    onSensorsClick: () -> Unit,
    onCollectClick: () -> Unit,
    onVisualizationClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),

        // Center everything horizontally.
        horizontalAlignment = Alignment.CenterHorizontally,

        // Center everything vertically.
        verticalArrangement = Arrangement.Center
    ) {

        // Application title.
        Text(
            text = "Motion Tracking Platform",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Short description displayed underneath the title.
        Text(
            text = "Biomechatronics Education",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        /*
         * BUTTON 1
         *
         * Takes the user to the sensor configuration page.
         */
        Button(
            onClick = onSensorsClick,
            modifier = Modifier
                .width(330.dp)
                .height(60.dp)
        ) {
            Text("Connect and Configure Sensors")
        }

        Spacer(modifier = Modifier.height(20.dp))

        /*
         * BUTTON 2
         *
         * Takes the user to the data collection page.
         */
        Button(
            onClick = onCollectClick,
            modifier = Modifier
                .width(330.dp)
                .height(60.dp)
        ) {
            Text("Collect Data")
        }

        Spacer(modifier = Modifier.height(20.dp))

        /*
         * BUTTON 3
         *
         * Takes the user to the visualization page.
         */
        Button(
            onClick = onVisualizationClick,
            modifier = Modifier
                .width(330.dp)
                .height(60.dp)
        ) {
            Text("Data Visualization")
        }
    }
}


/*
 * SENSOR CONFIGURATION PAGE
 *
 * This is currently a placeholder.
 *
 * Later, this page can contain the controls used to connect
 * and configure up to nine WIT Motion IMU sensors.
 */
@Composable
fun SensorConfigurationPage(
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Connect and Configure Sensors",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Sensor configuration controls will be displayed here.",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Returns the user to the landing page.
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .width(200.dp)
                .height(55.dp)
        ) {
            Text("Back to Home")
        }
    }
}


/*
 * COLLECT DATA PAGE
 *
 * This is currently a placeholder.
 *
 * Eventually this page will contain the controls for starting
 * and stopping simultaneous data collection from the selected
 * IMU sensors.
 */
@Composable
fun CollectDataPage(
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Collect Data",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Data collection controls will be displayed here.",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Returns the user to the landing page.
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .width(200.dp)
                .height(55.dp)
        ) {
            Text("Back to Home")
        }
    }
}


/*
 * DATA VISUALIZATION PAGE
 *
 * This is currently a placeholder.
 *
 * The team's visualization functionality can eventually
 * be integrated into this page.
 */
@Composable
fun DataVisualizationPage(
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Data Visualization",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "3D motion data visualization will be displayed here.",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Returns the user to the landing page.
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .width(200.dp)
                .height(55.dp)
        ) {
            Text("Back to Home")
        }
    }
}


/*
 * ANDROID STUDIO PREVIEW
 *
 * This allows the landing page to be viewed inside Android
 * Studio without running the application on the emulator.
 */
@Preview(
    showBackground = true,
    widthDp = 800,
    heightDp = 480
)
@Composable
fun LandingPagePreview() {

    Biomechatronics_initial_commitTheme {

        /*
         * These functions do nothing in Preview mode because
         * navigation only needs to work when the app is running.
         */
        LandingPage(
            onSensorsClick = {},
            onCollectClick = {},
            onVisualizationClick = {}
        )
    }
}