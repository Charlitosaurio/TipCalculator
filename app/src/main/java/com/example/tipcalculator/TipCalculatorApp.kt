package com.example.tipcalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TipCalculatorApp() {

    var amountInput by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(15.0) }
    var roundUp by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipAmount = if (roundUp) Math.ceil(amount * tipPercent / 100)
    else amount * tipPercent / 100
    val total = amount + tipAmount

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculadora de Propinas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Monto de la cuenta ($)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Porcentaje: ${tipPercent.toInt()}%", fontWeight = FontWeight.Medium)

        Slider(
            value = tipPercent.toFloat(),
            onValueChange = { tipPercent = it.toDouble() },
            valueRange = 0f..50f,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(10.0, 15.0, 18.0, 20.0).forEach { pct ->
                Button(
                    onClick = { tipPercent = pct },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("${pct.toInt()}%")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Redondear propina")
            Switch(checked = roundUp, onCheckedChange = { roundUp = it })
        }

        HorizontalDivider()

        Text("Propina: $${"%.2f".format(tipAmount)}", fontSize = 18.sp)
        Text("Total: $${"%.2f".format(total)}", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Text(
            "La propina se actualiza automáticamente",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.outline
        )
    }
}