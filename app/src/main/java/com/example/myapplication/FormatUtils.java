package com.example.myapplication;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtils {

    private static final NumberFormat AMOUNT_FORMAT = NumberFormat.getInstance(new Locale("vi", "VN"));
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    private FormatUtils() {
    }

    public static String formatAmount(double amount) {
        return AMOUNT_FORMAT.format(amount) + " đ";
    }

    public static String formatDate(long millis) {
        return DATE_FORMAT.format(new Date(millis));
    }
}
