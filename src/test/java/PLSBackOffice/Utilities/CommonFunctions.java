package PLSBackOffice.Utilities;

import com.microsoft.playwright.Locator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;

public class CommonFunctions {

    /**
     * Date Formatter
     * from
     * dd/M/yyyy
     * to
     * dd MMM yyyy
     *
     * @param inputDate
     * @return
     */
    public static String dateFormatter(String inputDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/M/yyyy").parse(inputDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat("dd MMM yyyy").format(date);

    }

    public static String dateFormatter(LocalDate localDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-M-dd").parse("" + localDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new SimpleDateFormat("MMM d, yyyy").format(date);
    }

    public static Object findMaxOfDoubleList(Locator listLocator) {
        List<String> list = listLocator
                .allTextContents();

        Optional<Double> num = list
                .stream()
                .filter(x -> !x.equals(""))
                .map(Double::parseDouble)
                .collect(Collectors.toList())
                .stream()
                .max(Double::compareTo);
        if (!num.isPresent()) {
            return "";
        }
        return num.get();
    }

    public static Object findMinOfDoubleList(Locator listLocator) {
        List<String> list = listLocator
                .allTextContents();

        list.replaceAll(x -> x.replaceFirst("^0.0$", ""));

        Optional<Double> num = list
                .stream()
                .filter(x -> !x.equals(""))
                .map(Double::parseDouble)
                .collect(Collectors.toList())
                .stream()
                .min(Double::compareTo);
        if (!num.isPresent()) {
            return "";
        }
        return num.get();
    }

    public static Object addSizeList(Locator listLocator) {
        List<String> list = listLocator.allTextContents();
        if (list.isEmpty()) {
            return "";
        }

        list.replaceAll(x -> x.replaceFirst("mn$", ""));

        Double num = list
                .stream()
                .filter(x -> !x.equals(""))
                .mapToDouble(Double::parseDouble)
                .sum();

        return num;
    }

    public static Object parse(String string) {
        if (!string.equalsIgnoreCase("")) {
            return parseDouble(string);
        }
        return "";
    }
}
