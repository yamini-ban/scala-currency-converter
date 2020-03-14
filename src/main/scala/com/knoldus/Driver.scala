package com.knoldus

object Driver extends App with CurrencyConverter {

  val convertFrom: Int = 1
  val convertTo: Int = 7
  val amount = 500
  val result = convertedValue(convertFrom, convertTo, amount)
  println(s"500 australian dollar = $result japanese yen")
}
