package com.mockadoodledoo.generators.primitive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.mockadoodledoo.generators.options.PrimitiveOptions;

public class BooleanGenerator {
    public static List<Boolean> of(int numberOfElements, PrimitiveOptions options) {
        List<Boolean> result = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            result.add(ThreadLocalRandom.current().nextBoolean());
        }
        return result;
    }
}
