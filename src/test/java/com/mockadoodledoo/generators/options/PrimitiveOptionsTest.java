package com.mockadoodledoo.generators.options;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.mockadoodledoo.generators.types.Primitive;

public class PrimitiveOptionsTest {

    private static final String ZERO = "0";
    private static final String ONE = "1";

    @Test
    public void booleanNoBoundsSuccess() {
        var po = new PrimitiveOptions(Primitive.BOOLEAN);
        assertEquals(Primitive.BOOLEAN, po.getType());
        assertEquals(ZERO, po.getLowerBound());
        assertEquals(ONE, po.getUpperBound());
    }

    @Test
    public void booleanBoundsIgnoredSuccess() {
        var po = new PrimitiveOptions(Primitive.BOOLEAN, 10, 100);
        assertEquals(Primitive.BOOLEAN, po.getType());
        assertEquals(ZERO, po.getLowerBound());
        assertEquals(ONE, po.getUpperBound());
    }

    @Test
    public void integerNoBoundsSuccess() {
        var po = new PrimitiveOptions(Primitive.INTEGER);
        assertEquals(Primitive.INTEGER, po.getType());
        assertEquals(ZERO, po.getLowerBound());
        assertEquals(Long.toString(Long.MAX_VALUE), po.getUpperBound());
    }

    @Test
    public void integerUpperBoundSuccess() {
        long bound = 10_000;
        var po = new PrimitiveOptions(Primitive.INTEGER, bound);
        assertEquals(Primitive.INTEGER, po.getType());
        assertEquals(ZERO, po.getLowerBound());
        assertEquals(Long.toString(bound), po.getUpperBound());
    }

    @Test
    public void integerUpperBoundNegativeFail() {
        long bound = -10_000;
        assertThrows(IllegalArgumentException.class,
                () -> new PrimitiveOptions(Primitive.INTEGER, bound));
    }

    @Test
    public void integerLowerAndUpperBoundNegativeFail() {
        long lowerBound = 10_000;
        long upperBound = -10_000;
        assertThrows(IllegalArgumentException.class,
                () -> new PrimitiveOptions(Primitive.INTEGER, lowerBound, upperBound));
    }

    @Test
    public void integerLowerAndUpperBoundEqualFail() {
        long lowerBound = 0;
        long upperBound = 0;
        assertThrows(IllegalArgumentException.class,
                () -> new PrimitiveOptions(Primitive.INTEGER, lowerBound, upperBound));
    }

    @Test
    public void intergerLowerAndUpperBoundSuccess() {
        long lowerBound = 0;
        long upperBound = 10_000;
        var po = new PrimitiveOptions(Primitive.INTEGER, lowerBound, upperBound);
        assertEquals(Primitive.INTEGER, po.getType());
        assertEquals(ZERO, po.getLowerBound());
        assertEquals(Long.toString(upperBound), po.getUpperBound());
    }
}
