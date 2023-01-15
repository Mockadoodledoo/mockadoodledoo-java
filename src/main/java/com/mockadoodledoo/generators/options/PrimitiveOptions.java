package com.mockadoodledoo.generators.options;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import com.mockadoodledoo.generators.types.Primitive;

public class PrimitiveOptions {
    private static DecimalFormat df =
            new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    static {
        df.setMaximumFractionDigits(340); // max allowed digits + no precision loss
    }

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
        this(type, validateLowerBoundToString(lowerBound, upperBound), Long.toString(upperBound));
    }

    public PrimitiveOptions(Primitive type, double lowerBound, double upperBound) {
        this(type, validateLowerBoundToString(lowerBound, upperBound), df.format(upperBound));
    }

    private PrimitiveOptions(Primitive type, String lowerBound, String upperBound) {
        if (upperBound.equals(lowerBound)) {
            throw new IllegalArgumentException("Bounds cannot be equal.");
        }
        switch (type) {
            case BOOLEAN -> {
                lowerBound = "0";
                upperBound = "1";
            }
            case FLOATING_POINT, INTEGER -> {
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

    private static String validateLowerBoundToString(long lowerBound, long upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        }
        return Long.toString(lowerBound);
    }

    private static String validateLowerBoundToString(double lowerBound, double upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        }
        return df.format(lowerBound);
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
