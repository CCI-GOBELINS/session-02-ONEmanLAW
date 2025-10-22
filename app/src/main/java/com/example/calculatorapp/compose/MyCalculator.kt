package com.example.calculatorapp.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.Calculator

/*
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
*/


/*
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
*/


@Composable
fun MyCalculator(modifier: Modifier = Modifier) {
    val calculator = remember { Calculator() }

    val number1 = remember { mutableStateOf("") }
    val number2 = remember { mutableStateOf("") }
    val operator = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }

    fun onDigit(d: String) {
        if (operator.value.isEmpty()) number1.value += d else number2.value += d
    }
    fun onOp(o: String) {
        if (number1.value.isNotEmpty()) operator.value = o
    }
    fun onEqual() {
        if (number1.value.isEmpty() || number2.value.isEmpty() || operator.value.isEmpty()) return
        result.value = when (operator.value) {
            "+" -> calculator.add(number1.value, number2.value)
            "-" -> calculator.sub(number1.value, number2.value)
            "*" -> calculator.mul(number1.value, number2.value)
            "/" -> calculator.div(number1.value, number2.value)
            else -> ""
        }
    }
    fun onClear() {
        number1.value = "";
        number2.value = "";
        operator.value = "";
        result.value = ""
    }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        val display = if (operator.value.isEmpty()) number1.value
        else "${number1.value} ${operator.value} ${number2.value} ="
        Text(text = display, fontSize = 32.sp)

        Spacer(Modifier.height(24.dp))



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(onClick = { onDigit("7") },
                modifier = Modifier.size(70.dp)) { Text("7", fontSize = 20.sp) }
            ElevatedButton(onClick = { onDigit("8") },
                modifier = Modifier.size(70.dp)) { Text("8", fontSize = 20.sp) }
            ElevatedButton(onClick = { onDigit("9") },
                modifier = Modifier.size(70.dp)) { Text("9", fontSize = 20.sp) }
            ElevatedButton(onClick = { onOp("/") },
                modifier = Modifier.size(70.dp)) { Text("/", fontSize = 20.sp) }
        }

        Spacer(Modifier.height(12.dp))



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(onClick = { onDigit("4") },
                modifier = Modifier.size(70.dp)) { Text("4", fontSize = 20.sp) }
            ElevatedButton(onClick = { onDigit("5") },
                modifier = Modifier.size(70.dp)) { Text("5", fontSize = 20.sp) }
            ElevatedButton(onClick = { onDigit("6") },
                modifier = Modifier.size(70.dp)) { Text("6", fontSize = 20.sp) }
            ElevatedButton(onClick = { onOp("*") },
                modifier = Modifier.size(70.dp)) { Text("*", fontSize = 20.sp) }
        }

        Spacer(Modifier.height(12.dp))



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(onClick = { onDigit("1") },
                modifier = Modifier.size(70.dp)) { Text("1", fontSize = 20.sp) }
            ElevatedButton(onClick = { onDigit("2") },
                modifier = Modifier.size(70.dp)) { Text("2", fontSize = 20.sp) }
            ElevatedButton(onClick = { onDigit("3") },
                modifier = Modifier.size(70.dp)) { Text("3", fontSize = 20.sp) }
            ElevatedButton(onClick = { onOp("-") },
                modifier = Modifier.size(70.dp)) { Text("-", fontSize = 20.sp) }
        }

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(onClick = { onDigit("0") },
                modifier = Modifier.size(70.dp)) { Text("0", fontSize = 20.sp) }
            ElevatedButton(onClick = { onClear() },
                modifier = Modifier.size(70.dp)) { Text("C", fontSize = 20.sp) }
            ElevatedButton(onClick = { onEqual() },
                modifier = Modifier.size(70.dp)) { Text("=", fontSize = 20.sp) }
            ElevatedButton(onClick = { onOp("+") },
                modifier = Modifier.size(70.dp)) { Text("+", fontSize = 20.sp) }
        }

        Spacer(Modifier.height(20.dp))
        Text(text = "Result : ${result.value}", fontSize = 20.sp)
    }
}

@Preview()
@Composable
fun MyPreview() {
    MyCalculator()
}