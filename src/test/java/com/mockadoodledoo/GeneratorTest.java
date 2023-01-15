package com.mockadoodledoo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import com.mockadoodledoo.generators.options.PrimitiveOptions;
import com.mockadoodledoo.generators.types.Primitive;

public class GeneratorTest {

    @Test
    public void negativeElementsTest() {
        assertThrows(IllegalArgumentException.class, () -> Generator.with(-1));
    }

    @Test
    public void positiveElementsTest() {
        assertDoesNotThrow(() -> Generator.with(10));
    }

    @Test
    public void columnMismatchTest() {
        assertThrows(IllegalArgumentException.class,
                () -> Generator.with(5, "test column")
                        .primitive(new PrimitiveOptions(Primitive.BOOLEAN))
                        .primitive(new PrimitiveOptions(Primitive.BOOLEAN)));
    }

    @Test
    public void booleanGeneratorTest() {
        assertDoesNotThrow(
                () -> Generator.with(10).primitive(new PrimitiveOptions(Primitive.BOOLEAN)));
    }

    @Test
    public void booleanGeneratorNoColumns() {
        assertDoesNotThrow(
                () -> Generator.with(10).primitive(new PrimitiveOptions(Primitive.BOOLEAN))
                        .primitive(new PrimitiveOptions(Primitive.BOOLEAN)));
    }

    @Test
    public void integerGeneratorTest() {
        assertDoesNotThrow(
                () -> Generator.with(10).primitive(new PrimitiveOptions(Primitive.INTEGER)));
    }

    @Test
    public void floatingPointGeneratorTest() {
        assertDoesNotThrow(
                () -> Generator.with(10).primitive(new PrimitiveOptions(Primitive.FLOATING_POINT)));
    }
}
