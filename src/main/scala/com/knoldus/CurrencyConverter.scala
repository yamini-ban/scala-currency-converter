package com.knoldus

/**
 * CurrencyConverter trait which convert given amount from one currency to another
 */
trait CurrencyConverter {

  /**
   * This method takes in currencies as string to convert amount to desired currency
   *
   * @param convertFromCurrency String to convert from
   * @param convertToCurrency   String to convert to
   * @param amount              to be converted
   * @return transformed amount
   */
  def convertedValue(convertFromCurrency: String, convertToCurrency: String, amount: Double): Double = {

    if (validateConvertFromCurrency(convertFromCurrency) && validateConvertToCurrency(convertToCurrency)) {
      convertedValue(getCurrencies.indexOf(convertFromCurrency.toLowerCase),
        getCurrencies.indexOf(convertToCurrency.toLowerCase), amount)
    }
    else {
      throw new InterruptedException("Please provide acceptable currency(s).")
    }
  }

  /**
   * this method converts amount of one currency to another
   *
   * @param convertFromIndex currency to convert from
   * @param convertToIndex   currency to convert to
   * @param amount           to be converted
   * @return transformed amount
   */
  def convertedValue(convertFromIndex: Int, convertToIndex: Int, amount: Double): Double = {
    if (validateConvertFromIndex(convertFromIndex) && validateConvertToIndex(convertToIndex, convertFromIndex) && validateAmount(amount)) {
      amount * CurrencyConverter.CURRENCY_RATES(convertFromIndex)(convertToIndex)
    }
    else {
      throw new IndexOutOfBoundsException("Please provide acceptable input.")
    }
  }

  /**
   * validate the convert from currency
   *
   * @param convertFromIndex is the index of convert from currency in currencies array
   * @return true if currency is available
   */
  private def validateConvertFromIndex(convertFromIndex: Int) = convertFromIndex >= 0 &
    convertFromIndex < CurrencyConverter.CURRENCY_RATES.length

  /**
   * validate the convert to currency
   *
   * @param convertToIndex is the index of convert to currency in currencies array
   * @return true if currency is available
   */
  private def validateConvertToIndex(convertToIndex: Int, convertFromIndex: Int) = convertToIndex >= 0 &
    convertToIndex < CurrencyConverter.CURRENCY_RATES(convertFromIndex).length

  /**
   * validate the amount to be converted
   *
   * @param amount value to be converted
   * @return true if amount is >= 0
   */
  private def validateAmount(amount: Double) = amount >= 0.0

  private def validateConvertFromCurrency(convertFromCurrency: String) =
    getCurrencies.indexOf(convertFromCurrency.toLowerCase) >= 0

  /**
   * @return array of currencies.
   */
  def getCurrencies: Array[String] = CurrencyConverter.CURRENCIES

  private def validateConvertToCurrency(convertToCurrency: String) =
    getCurrencies.indexOf(convertToCurrency.toLowerCase) >= 0

}

object CurrencyConverter {

  val CURRENCIES: Array[String] = Array("afghan afghani", "australian dollars", "bhutan ngultrum", "chinese yuan", "european euro",
    "gibraltar pound", "indian rupees", "japanese yen", "kuwaiti dinar", "poland zloty")

  val AFGHAN_AFGHANI__TO_OTHERS: Array[Double] = Array(1.0, 0.02, 0.93, 0.091, 0.012, 0.010, 0.93, 1.43, 0.004, 0.051)

  val AUSTRALIAN_DOLLARS_TO_OTHERS: Array[Double] = Array(51.25, 1.0, 47.56, 4.67, 0.61, 0.54, 47.72, 73.24, 0.20, 2.61)

  val BHUTANI_NGULTRUM_TO_OTHERS: Array[Double] = Array(1.08, 0.021, 1.0, 0.098, 0.013, 0.011, 1.0, 1.54, 0.0043, 0.055)

  val CHINESE_YUAN_TO_OTHERS: Array[Double] = Array(10.97, 0.21, 10.18, 1.0, 0.13, 0.12, 10.21, 15.68, 0.043, 0.56)

  val EUROPEAN_EURO_TO_OTHERS: Array[Double] = Array(84.07, 1.64, 78.01, 7.66, 1.0, 0.9, 78.28, 120.11, 0.33, 4.27)

  val GIBRALTAR_POUND_TO_OTHERS: Array[Double] = Array(95.79, 1.84, 87.71, 8.64, 1.11, 1.0, 87.68, 133.69, 0.37, 4.78)

  val INDIAN_RUPEES_TO_OTHERS: Array[Double] = Array(1.07, 0.021, 1.0, 0.098, 0.013, 0.011, 1.0, 1.53, 0.004, 0.06)

  val JAPANESE_YEN_TO_OTHERS: Array[Double] = Array(0.7, 0.014, 0.65, 0.064, 0.008, 0.008, 0.65, 1.0, 0.003, 0.036)

  val KUWAITI_DINAR_TO_OTHERS: Array[Double] = Array(252.47, 4.93, 234.28, 23.02, 3.0, 2.67, 235.09, 360.71, 1.0, 12.83)

  val POLAND_ZLOTY_TO_OTHERS: Array[Double] = Array(19.67, 0.38, 18.25, 1.79, 0.23, 0.21, 18.32, 28.13, 0.078, 1.0)

  val CURRENCY_RATES: Array[Array[Double]] = Array(AFGHAN_AFGHANI__TO_OTHERS, AUSTRALIAN_DOLLARS_TO_OTHERS, BHUTANI_NGULTRUM_TO_OTHERS,
    CHINESE_YUAN_TO_OTHERS, EUROPEAN_EURO_TO_OTHERS, GIBRALTAR_POUND_TO_OTHERS, INDIAN_RUPEES_TO_OTHERS, JAPANESE_YEN_TO_OTHERS,
    KUWAITI_DINAR_TO_OTHERS, POLAND_ZLOTY_TO_OTHERS)

}

