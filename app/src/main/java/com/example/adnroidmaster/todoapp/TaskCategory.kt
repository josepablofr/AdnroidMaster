package com.example.adnroidmaster.todoapp

sealed class TaskCategory(var isSelected:Boolean = true) {  // con esto asignamos el atributo var "is Selected" a todos los objetos
    // si quisieramos aplicar ese atributo solo a 1 o 2 objetos, habría que convertirlos en "data class"
    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()

    // Los objetos no pueden recibir parámetros
}