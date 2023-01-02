package com.mockadoodledoo.generators.primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.mockadoodledoo.generators.options.PrimitiveOptions;

public class IntegerGenerator {
    public static List<Long> of(int numberOfElements, PrimitiveOptions options) {
        long lowerBound = Long.parseLong(options.getLowerBound());
        long upperBound = Long.parseLong(options.getUpperBound());
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        }
        long diff = Math.abs(lowerBound - upperBound);
        diff = diff < Long.MAX_VALUE ? diff + 1 : diff;
        List<Long> result = new ArrayList<>(numberOfElements);

        for (int i = 0; i < numberOfElements; i++) {
            result.add(ThreadLocalRandom.current().nextLong(diff) + lowerBound);
        }
        return result;
    }
}
