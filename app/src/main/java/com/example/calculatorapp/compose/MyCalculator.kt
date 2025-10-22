package com.example.calculatorapp.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CalculatorField(
    label: String,
    value: MutableState<String>,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        modifier = modifier,
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Cyan
        )
    )
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier.size(150.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = label)
    }
}


@Composable
fun MyCalculator(modifier: Modifier = Modifier) {
    val number1 = remember { mutableStateOf("") }
    val number2 = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }
    Column {
        CalculatorField(
            label = "Number 1",
            value = number1,
            modifier = modifier
        )

        CalculatorField(
            label = "Number 2",
            value = number2,
            modifier = modifier
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CalculatorButton(
                label = "+",
                onClick = {
                    result.value = (number1.value.toInt() + number2.value.toInt()).toString()

                },
                modifier = modifier
            )

            CalculatorButton(
                label = "-",
                onClick = {
                    result.value = (number1.value.toInt() - number2.value.toInt()).toString()
                },
                modifier = modifier
            )
        }

        Spacer(
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CalculatorButton(
                label = "/",
                onClick = {
                    result.value = (number1.value.toInt() / number2.value.toInt()).toString()
                },
                modifier = modifier
            )

            CalculatorButton(
                label = "*",
                onClick = {
                    result.value = (number1.value.toInt() * number2.value.toInt()).toString()
                },
                modifier = modifier
            )
        }


    }
}

@Preview()
@Composable
fun MyPreview() {
    MyCalculator()
}