package com.example.calculatorapp

class Calculator {
    fun add(a: String, b: String): String {
        val first = a.replace(',', ',').toDouble()
        val second = b.replace(',', ',').toDouble()
        return (first + second).toString()
    }

    fun sub(a: String, b: String): String {
        val first = a.replace(',', ',').toDouble()
        val second = b.replace(',', ',').toDouble()
        return (first - second).toString()
    }
    fun mul(a: String, b: String): String {
        val first = a.replace(',', ',').toDouble()
        val second = b.replace(',', ',').toDouble()
        return (first * second).toString()
    }
    fun div(a: String, b: String): String {
        val first = a.replace(',', ',').toDouble()
        val second = b.replace(',', ',').toDouble()
        return (first / second).toString()
    }
}

