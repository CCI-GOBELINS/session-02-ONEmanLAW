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

    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun onDigit(digit: String) {
        if (operator.isEmpty()) number1 += digit else number2 += digit
    }

    fun onOperator(op: String) {
        if (number1.isNotEmpty()) operator = op
    }

    fun onEqual() {
        if (number1.isEmpty() || number2.isEmpty() || operator.isEmpty()) return
        result = when (operator) {
            "+" -> calculator.add(number1, number2)
            "-" -> calculator.sub(number1, number2)
            "*" -> calculator.mul(number1, number2)
            "/" -> calculator.div(number1, number2)
            else -> ""
        }
    }

    fun onClear() {
        number1 = "";
        number2 = "";
        operator = "";
        result = ""
    }

    @Composable
    fun Key(label: String) {
        ElevatedButton(
            onClick = {
                when {
                    label.all { it.isDigit() } -> onDigit(label)
                    label in listOf("+", "-", "*", "/") -> onOperator(label)
                    label == "=" -> onEqual()
                    label == "C" -> onClear()
                }
            },
            modifier = Modifier.size(72.dp)
        ) {
            Text(label, fontSize = 20.sp)
        }
    }

    @Composable
    fun RowKeys(vararg labels: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.forEach { Key(it) }
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        val display = if (operator.isEmpty()) number1 else "$number1 $operator $number2 ="
        Text(display, fontSize = 32.sp)
        Spacer(Modifier.height(24.dp))


        RowKeys("7","8","9","/")
        Spacer(Modifier.height(12.dp))
        RowKeys("4","5","6","*")
        Spacer(Modifier.height(12.dp))
        RowKeys("1","2","3","-")
        Spacer(Modifier.height(12.dp))
        RowKeys("0","C","=","+")

        Spacer(Modifier.height(20.dp))
        Text("Result : $result", fontSize = 20.sp)
    }
}

@Preview()
@Composable
fun MyPreview() {
    MyCalculator()
}