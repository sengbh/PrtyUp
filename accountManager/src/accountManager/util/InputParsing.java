package accountManager.util;

public class InputParsing {

    /**
     * This takes a string with a decimal and returns an integer in hundreths.
     * Equivalent to the number of cents in most currencies.
     * @param input String with one decimal and numbers.
     * @return Integer in cents.
     */
    public static int inputToInt(String inputText) {

        int value;

        if (inputText.contains(".")) {
            int i = inputText.indexOf('.');
            StringBuilder sb = new StringBuilder(inputText);
            sb.deleteCharAt(i);
            inputText = sb.toString();
            inputText = inputText.substring(0, i+2);
            value = Integer.parseInt(inputText);

        }
        else { value = (Integer.parseInt(inputText) * 100); }

        return value;
    }
}
