package com.bcafinance.training

fun main() {
    println("Hello")
    print("test")
    angka(5)

    //mutable : nilainya bisa dirubah
    var nama = "dewi";
    nama = "dewa"

    var angkaasli = 2

    var tampung = nama + angkaasli
    print(tampung)

//        //imutable : nilainya tidak bisa dirubah
//        val umur = 5
//        umur = 1 //error value cant be reassigned

    print("maskkan nama: ")
            var namaa = readLine()

    print(namaa!!.length)
}

fun angka(angka1: Int) {
    println("angkanya adalah : $angka1")

}
