package com.example.smd_po_module;

import java.util.regex.Pattern;

/**
 * Plain-Java validation rules shared by the sign-up, officer and update forms.
 * Kept free of Android types so it can be unit tested on the JVM.
 */
public final class FormRules {

    /** Letters, spaces and underscores: names, degrees, tools. */
    public static final Pattern NAME_CHARS = Pattern.compile("[A-Za-z _]");
    /** Letters, digits and underscores: passwords, experience. */
    public static final Pattern ALPHANUMERIC_CHARS = Pattern.compile("[A-Za-z0-9_]");
    /** Digits and a decimal point: GPA. */
    public static final Pattern DECIMAL_CHARS = Pattern.compile("[0-9.]");

    private static final Pattern EMAIL =
            Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}");
    private static final Pattern GPA = Pattern.compile("\\d(\\.\\d{1,2})?");
    private static final double MAX_GPA = 4.0;

    private FormRules() {
    }

    /** True when every character in {@code text[start, end)} matches {@code allowed}. */
    public static boolean onlyAllowed(CharSequence text, int start, int end, Pattern allowed) {
        for (int i = start; i < end; i++) {
            if (!allowed.matcher(String.valueOf(text.charAt(i))).matches()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmail(String value) {
        return value != null && EMAIL.matcher(value.trim()).matches();
    }

    /** True when no value is null or blank. */
    public static boolean allFilled(String... values) {
        for (String value : values) {
            if (value == null || value.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /** A GPA on the 4.0 scale with at most two decimals, e.g. "3", "3.5", "2.75". */
    public static boolean isGpa(String value) {
        if (value == null || !GPA.matcher(value.trim()).matches()) {
            return false;
        }
        return Double.parseDouble(value.trim()) <= MAX_GPA;
    }

    /** Parses a whole number of years; returns -1 when the text is blank, negative or not a number. */
    public static int parseYears(String value) {
        if (value == null) {
            return -1;
        }
        try {
            int years = Integer.parseInt(value.trim());
            return years >= 0 ? years : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
