package com.capitansolnan.fitnessapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDesplegable (
    label: String,
    opcions: List<String>,
    valorSeleccionat: String,
    onValorSeleccionat: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var desplegat by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = desplegat,
        onExpandedChange = { desplegat = it },
        modifier = modifier
    ) {

        OutlinedTextField(
            value = valorSeleccionat,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = desplegat
                )
            },
            modifier = Modifier
                .menuAnchor(
                    ExposedDropdownMenuAnchorType.PrimaryNotEditable
                ).fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = desplegat,
            onDismissRequest = {
                desplegat = false
            }
        ) {
            opcions.forEach { opcio ->

                DropdownMenuItem(
                    text = { Text(opcio) },
                    onClick = {
                        onValorSeleccionat(opcio)
                        desplegat = false
                    }
                )
            }
        }
    }
}