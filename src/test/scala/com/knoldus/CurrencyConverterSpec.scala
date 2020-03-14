
package com.knoldus

import org.scalatest._

class CurrencyConverterSpec extends FlatSpec with BeforeAndAfterAll {
  val currencyConverter: CurrencyConverter = new CurrencyConverter {}

  "convertedValue method" should "throw exception on invalid transformation" in {
    try {
      val actualResult = currencyConverter.convertedValue(CurrencyConverterConstants.CONVERT_FROM_INVALID
        , CurrencyConverterConstants.CONVERT_TO_CURRENCY_INDEX
        , CurrencyConverterConstants.AMOUNT)
      assert("" == actualResult)
    }
    catch {
      case exception: IndexOutOfBoundsException => assert(exception.getMessage == "Please provide acceptable input.")
    }
  }

  "convertedValue method" should "return transformed value" in {
    val actualResult = currencyConverter.convertedValue(CurrencyConverterConstants.CONVERT_FROM_CURRENCY_INDEX
      , CurrencyConverterConstants.CONVERT_TO_CURRENCY_INDEX
      , CurrencyConverterConstants.AMOUNT)
    val expectedResult = CurrencyConverterConstants.CONVERTED_VALUE
    assert(actualResult == expectedResult)
  }

  "convertedValue method takes currencies as string and " should "return transformed value" in {
    val actualResult = currencyConverter.convertedValue(CurrencyConverterConstants.CONVERT_FROM_CURRENCY,
      CurrencyConverterConstants.CONVERT_TO_CURRENCY, CurrencyConverterConstants.AMOUNT)
    val expectedResult = CurrencyConverterConstants.CONVERTED_VALUE
    assert(expectedResult == actualResult)
  }

  "convertedValue method which takes currencies as String" should "throw exception on invalid transformation" in {
    try {
      val actualResult = currencyConverter.convertedValue(CurrencyConverterConstants.CONVERT_FROM_INVALID_CURRENCY,
        CurrencyConverterConstants.CONVERT_TO_CURRENCY, CurrencyConverterConstants.AMOUNT)
      assert("" == actualResult)
    }
    catch {
      case exception: IndexOutOfBoundsException => assert(exception.getMessage == "Please provide acceptable input.")
      case exception: InterruptedException => assert(exception.getMessage == "Please provide acceptable currency(s).")
    }
  }

}
