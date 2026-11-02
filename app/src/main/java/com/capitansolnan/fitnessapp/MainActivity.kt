package com.capitansolnan.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capitansolnan.fitnessapp.ui.screen.EditProfileScreen
import com.capitansolnan.fitnessapp.ui.screen.ProfileScreen
import com.capitansolnan.fitnessapp.ui.theme.FitnessAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        FitnessApp()
                    }
                }
            }
        }
    }
}

@Composable
fun FitnessApp() {
    var nom: String by remember { mutableStateOf("Laia Soler") }
    var email: String by remember { mutableStateOf("laia.soler@example.com") }
    var dataNaixement: Long by remember { mutableStateOf(946684800000L) }
    var genere: String by remember { mutableStateOf("Dona") }
    var editant: Boolean by remember { mutableStateOf(false) }
    var pes: Double by remember { mutableStateOf(60.0) }
    var pesObjectiu: Double by remember { mutableStateOf(55.0) }
    var alçada: Int by remember { mutableStateOf(170) }
    var objectiu: String by remember { mutableStateOf("Guanyar massa muscular") }
    var nivellActivitat: String by remember { mutableStateOf("Moderat") }

    if (editant) {
        EditProfileScreen(
            nom, email, dataNaixement, genere, pes, pesObjectiu, alçada, objectiu,
            onSave = { nomN, emailN, dataNaixementN, genereN, pesN, pesObjectiuN, alçadaN ->
                nom = nomN
                email = emailN
                dataNaixement = dataNaixementN
                genere = genereN
                pes = pesN
                pesObjectiu = pesObjectiuN
                alçada = alçadaN
                editant = false
            }
        )
    } else {
        val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.format(Date(dataNaixement))

        ProfileScreen(
            nom = nom,
            email = email,
            dataNaixement = formattedDate,
            genere = genere,
            alçada = alçada,
            pes = pes,
            objectiu = objectiu,
            nivellActivitat = nivellActivitat,
            editar = { editant = true }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessAppPreview() {
    FitnessAppTheme {
        FitnessApp()
    }
}
