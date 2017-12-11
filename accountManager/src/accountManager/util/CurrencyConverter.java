package accountManager.util;

public class CurrencyConverter {

    static final double USD_TO_EUR = 0.79;
    static final double USD_TO_JPY = 94.1;

    /**
     * Converts euro values to USD. Drops any decimal after cents.
     * @param value in EUR
     * @return value in USD
     */
    public static int eurToUsd(int val){
        return ((int)(val * (1/USD_TO_EUR)));
    }
    /**
     * Converts yen values to USD. Drops any decimal after cents.
     * @param value in JPY
     * @return value in USD
     */
    public static int jpyToUsd(int val) {
        return ((int)(val * (1/USD_TO_JPY)));
    }
    /**
     * Converts USD values to EUR. Drops any decimal after cents.
     * @param value in USD
     * @return value in EUR
     */
    public static int usdToEur(int val) {
        return((int)(val * USD_TO_EUR));
    }
    /**
     * Converts USD values to JPY. Drops any decimal after cents.
     * @param value in USD
     * @return value in JPY
     */
    public static int usdToJpy(int val) {
        return((int)(val * USD_TO_JPY));
    }
}
