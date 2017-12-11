package test;

import static org.junit.Assert.*;
import accountManager.util.*;
import org.junit.Test;

public class CurrencyConverterTest {

    @Test
    public void testEurToUsd() {
        int testVal = 12658;
        assertEquals(testVal, CurrencyConverter.eurToUsd(10000));
        testVal = 197;
        assertEquals(testVal, CurrencyConverter.eurToUsd(156));
        testVal = 1235;
        assertEquals(testVal, CurrencyConverter.eurToUsd(976));
    }

    @Test
    public void testJpyToUsd() {
        int testVal = 106;
        assertEquals(testVal, CurrencyConverter.jpyToUsd(10000));
        testVal = 1;
        assertEquals(testVal, CurrencyConverter.jpyToUsd(156));
        testVal = 10;
        assertEquals(testVal, CurrencyConverter.jpyToUsd(976));
    }

    @Test
    public void testUsdToEur() {
        int testVal = 7900;
        assertEquals(testVal, CurrencyConverter.usdToEur(10000));
        testVal = 123;
        assertEquals(testVal, CurrencyConverter.usdToEur(156));
        testVal = 771;
        assertEquals(testVal, CurrencyConverter.usdToEur(976));
    }

    @Test
    public void testUsdToJpy() {
        int testVal = 941000;
        assertEquals(testVal, CurrencyConverter.usdToJpy(10000));
        testVal = 14679;
        assertEquals(testVal, CurrencyConverter.usdToJpy(156));
        testVal = 91841;
        assertEquals(testVal, CurrencyConverter.usdToJpy(976));
    }

}
