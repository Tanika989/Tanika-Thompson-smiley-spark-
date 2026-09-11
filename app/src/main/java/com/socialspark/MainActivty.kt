import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialSparkApp()
        }
    }
}

@Composable
fun SocialSparkApp() {
    var timeOfDay by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var sparkMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD6EEFF))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Social Spark", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("A small spark to brighten your day", fontSize = 12.sp)
            }
            Text("🙂", fontSize = 30.sp)
        }

        Spacer(Modifier.height(20.dp))

        Text("What kind of day is it?", fontSize = 14.sp)
        OutlinedTextField(
            value = timeOfDay,
            onValueChange = { timeOfDay = it },
            placeholder = { Text("e.g. morning, afternoon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                sparkMessage = when (timeOfDay.lowercase()) {
                    "morning" -> "Good Morning! Text a friend 'Have a great day!'"
                    "afternoon" -> "Afternoon slump? Send a funny meme to someone."
                    "evening" -> "Evening! Call a family member for 5 mins."
                    else -> "It's a ${timeOfDay} day - Share a smile with someone near you!"
                }
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B), contentColor = Color.Black)
        ) {
            Text("Get My Spark")
        }

        Spacer(Modifier.height(12.dp))

        Text(
            "Enter a time of day and we'll give you a small way to connect.",
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp)
        )

        Spacer(Modifier.height(10.dp))

        OutlinedButton(onClick = { timeOfDay = ""; showDialog = false }, modifier = Modifier.fillMaxWidth()) {
            Text("Reset")
        }

        Spacer(Modifier.weight(1f))

        Text("😊", fontSize = 180.sp, modifier = Modifier.align(Alignment.CenterHorizontally))

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface(shape = MaterialTheme.shapes.medium, color = Color.White) {
                    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🙂", fontSize = 40.sp)
                        Text("Your Social Spark", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(Modifier.height(10.dp))
                        Text(sparkMessage)
                        Spacer(Modifier.height(15.dp))
                        Button(onClick = { showDialog = false }, modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B), contentColor = Color.Black)
                        ) {
                            Text("Thanks!")
                        }
                    }
                }
            }
        }

        Text("ST10545803 - Tanika Thompson", fontSize = 10.sp, color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}
