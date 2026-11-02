package com.capitansolnan.fitnessapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capitansolnan.fitnessapp.R
import java.util.Calendar

@Composable
fun ProfileScreen(
    nom: String,
    email: String,
    dataNaixement: String,
    genere: String,
    alçada: Int,
    pes: Double,
    objectiu: String,
    nivellActivitat: String,
    editar: () -> Unit,
) {
    Column() {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
            )
            Text(
                text = nom,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = email,
                fontSize = 16.sp,
                color = Color.Gray,
            )
        }

        Card(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        "Edat",
                        color = Color.Gray
                    )
                    val edat = calcularEdat(dataNaixement)
                    Text(
                        edat.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
                Column() {
                    Text(
                        "Genere",
                        color = Color.Gray
                    )
                    Text(
                        genere,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column() {
                    Text(
                        "Alçada",
                        color = Color.Gray
                    )
                    Text(
                        "$alçada cm",
                        fontWeight = FontWeight.Bold
                    )
                }
                Column() {
                    Text(
                        "Pes",
                        color = Color.Gray
                    )
                    Text(
                        "$pes kg",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Text(
            "Objectius",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
        )
        Card(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp
                ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(objectiu, Modifier.padding(8.dp))
        }

        Text(
            "Nivell d'activitat",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)

        )
        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(nivellActivitat, Modifier.padding(8.dp))
        }

    }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_edit),
            contentDescription = "Edit",
            alignment = Alignment.TopEnd,
            modifier = Modifier
                .size(64.dp)
                .clickable { editar() }
        )
    }

}

private fun calcularEdat(dataNaixement: String): Int {
    return try {
        val parts = dataNaixement.split("/")
        if (parts.size != 3) return 0

        val diaNaixement = parts[0].toInt()
        val mesNaixement = parts[1].toInt() - 1
        val anyNaixement = parts[2].toInt()

        val avui = Calendar.getInstance()
        val anyActual = avui.get(Calendar.YEAR)
        val mesActual = avui.get(Calendar.MONTH)
        val diaActual = avui.get(Calendar.DAY_OF_MONTH)

        var edat = anyActual - anyNaixement

        if (mesActual < mesNaixement || (mesActual == mesNaixement && diaActual < diaNaixement)) {
            edat--
        }

        if (edat < 0) 0 else edat
    } catch (_: Exception) {
        0
    }
}