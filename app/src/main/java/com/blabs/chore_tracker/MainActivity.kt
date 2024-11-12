package com.blabs.chore_tracker

// ... other imports ...
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.blabs.chore_tracker.ui.theme.Chore_TrackerTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ... other code ...
        setContent {
            Chore_TrackerTheme {
                ChoreListScreen()
            }
        }
    }
}

@Composable
fun ChoreListScreen() {
    val chorelist = remember { mutableStateListOf<Chore>() }
    var choreName by remember { mutableStateOf("") }
    var choreDescription by remember { mutableStateOf("") }

    Column {
        TextField(
            value = choreName,
            onValueChange = { choreName = it },
            label = { Text("Chore Name") }
        )
        TextField(
            value = choreDescription,
            onValueChange = { choreDescription = it },
            label = { Text("Chore Description") }
        )
    }

    Button(onClick = {
        chorelist.add(Chore(choreName, choreDescription))
        // clear input fields after adding chore
        choreName = ""
        choreDescription = ""
    }) {
        Text("Add Chore")
    }
}
data class Chore(
    val name: String,
    val description: String,
    val isCompleted: Boolean = false
    // ... other properties ...
)

@Composable
fun ChoreList(chores: List<Chore>) {
    LazyColumn {
        items(chores) { chore ->
            ChoreItem(chore)

        }
    }
}

@Composable
fun ChoreItem(chore: Chore) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
        Text(text = chore.name)
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = chore.isCompleted,
            onCheckedChange = { isChecked -> }
        )
    }
}