package com.mockadoodledoo.generators.primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.mockadoodledoo.generators.options.PrimitiveOptions;

public class FloatingPointGenerator {

    public static List<Double> of(int numberOfElements, PrimitiveOptions options) {
        double lowerBound = Double.parseDouble(options.getLowerBound());
        double upperBound = Double.parseDouble(options.getUpperBound());
        double diff = Math.abs(lowerBound - upperBound);
        List<Double> result = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            result.add(ThreadLocalRandom.current().nextDouble(diff) + lowerBound);
        }
        return result;
    }
}
