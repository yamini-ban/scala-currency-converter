package com.knoldus

import org.scalatest._

class CurrencyConverterSpec extends FlatSpec with BeforeAndAfterAll {
  var currencyConverter: CurrencyConverter = new CurrencyConverter {
    override val convertFromIndex: Int = 3
    override val convertToIndex: Int = 5
  }

  override def beforeAll(): Unit = {
    currencyConverter = new CurrencyConverter {
      override val convertFromIndex: Int = CurrencyConverterConstants.CONVERT_FROM_CURRENCY_INDEX
      override val convertToIndex: Int = CurrencyConverterConstants.CONVERT_TO_CURRENCY_INDEX
    }
  }

  override def afterAll(): Unit = {
    if (currencyConverter != null) {
      currencyConverter = null
    }
  }

  "convertedValue method" should "throw exception on invalid transformation" in {
    try {
      val actualResult = currencyConverter.convertedValue(CurrencyConverterConstants.CONVERT_FROM_INVALID, currencyConverter.getConvertToIndex,
        CurrencyConverterConstants.AMOUNT)
      assert("" == actualResult)
    }
    catch {
      case exception: IndexOutOfBoundsException => assert(exception.getMessage == "Please provide acceptable input.")
    }
  }

  "convertedValue method" should "return transformed value" in {
    val actualResult = currencyConverter.convertedValue(currencyConverter.getConvertFromIndex, currencyConverter.getConvertToIndex,
      CurrencyConverterConstants.AMOUNT)
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
