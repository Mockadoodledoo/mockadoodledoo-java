package com.mockadoodledoo.generators.options;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;
import com.mockadoodledoo.generators.types.Primitive;

public class PrimitiveOptions {
    private static DecimalFormat df =
            new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    static {
        df.setMaximumFractionDigits(340); // max allowed digits + no precision loss
    }

    private static Pattern fpPattern = Pattern.compile("[+-]?([0-9]*[.])?[0-9]+");
    private static Pattern intPattern = Pattern.compile("^[-+]?\\d+$");
    private Primitive type;
    private String lowerBound;
    private String upperBound;

    public PrimitiveOptions(Primitive type) {
        this(type, "0", getUpperBound(type));
    }

    public PrimitiveOptions(Primitive type, long upperBound) {
        this(type, 0, upperBound);
    }

    public PrimitiveOptions(Primitive type, double upperBound) {
        this(type, 0.0, upperBound);
    }

    public PrimitiveOptions(Primitive type, long lowerBound, long upperBound) {
        this(type, Long.toString(lowerBound), Long.toString(upperBound));
    }

    public PrimitiveOptions(Primitive type, double lowerBound, double upperBound) {
        this(type, df.format(lowerBound), df.format(upperBound));
    }

    private PrimitiveOptions(Primitive type, String lowerBound, String upperBound) {
        if (upperBound.equals(lowerBound)) {
            throw new IllegalArgumentException("Bounds cannot be equal.");
        }
        switch (type) {
            case BOOLEAN -> {
                break;
            }
            case FLOATING_POINT -> {
                if (!fpPattern.matcher(upperBound).matches()) {
                    throw new IllegalArgumentException(
                            "Upper bound is not a floating point value.");
                }
                if (!fpPattern.matcher(lowerBound).matches()) {
                    throw new IllegalArgumentException(
                            "Lower bound is not a floating point value.");
                }
            }
            case INTEGER -> {
                if (!intPattern.matcher(upperBound).matches()) {
                    throw new IllegalArgumentException("Upper bound is not an Integer value.");
                }
                if (!intPattern.matcher(lowerBound).matches()) {
                    throw new IllegalArgumentException("Lower bound is not an Integer value.");
                }
            }
            default -> {
                break;
            }
        }
        this.type = type;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    private static String getUpperBound(Primitive type) {
        return switch (type) {
            case INTEGER -> Long.toString(Long.MAX_VALUE);
            case FLOATING_POINT -> df.format(Double.MAX_VALUE);
            case BOOLEAN -> "1";
        };
    }

    public Primitive getType() {
        return type;
    }

    public String getLowerBound() {
        return lowerBound;
    }

    public String getUpperBound() {
        return upperBound;
    }
}
