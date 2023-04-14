package com.example.adnroidmaster

fun main() {
    var a = mutableListOf(1, 2, 3)
    println(suma(4, 4))
    println(ifBoolean())
    println(getMonth(9))
    println(array())
}

fun getMonth(i: Int) {
    when (i) {
        in 1..3 -> println("Q1")
        in 4..6 -> println("Q2")
        !in 1..6 -> println("código no válido")
    }
}

fun suma(a: Int = 3, b: Int = 2) = a + b

fun ifBoolean() {
    val soyfeliz: Boolean = true

    if (!soyfeliz) {
        println("verdad")
    }
}

fun array() {
    val weekDays = mutableListOf("L", "M", "R", "J", "V", "S", "D")
    for ((position, value) in weekDays.withIndex()){
        println("La posición $position, contiene $value")
    }
    val example = weekDays.filter { days -> days.contains("V") }
    println(example)

    weekDays.add(3,"Dormingo")
    println(weekDays)
}