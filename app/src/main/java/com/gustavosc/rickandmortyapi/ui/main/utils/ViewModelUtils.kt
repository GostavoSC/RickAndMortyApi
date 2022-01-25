package com.gustavosc.rickandmortyapi.ui.main.utils

fun getFiveRandomNumbers(): ArrayList<Int> {
    val numbers = arrayListOf(1)
    for (i in 1..4){
        numbers.add((0..826).random())
    }
    return numbers
}