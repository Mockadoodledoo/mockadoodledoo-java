package com.mockadoodledoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.mockadoodledoo.builders.CSVBuilder;
import com.mockadoodledoo.generators.options.PrimitiveOptions;
import com.mockadoodledoo.generators.primitive.BooleanGenerator;
import com.mockadoodledoo.generators.primitive.FloatingPointGenerator;
import com.mockadoodledoo.generators.primitive.IntegerGenerator;
import com.mockadoodledoo.generators.types.Primitive;

public class Generator {
    private static final int TO_STRING_PADDING = 25;
    private int elements;
    private String[] columns;
    private List<List<? extends Object>> items;

    /**
     * Creates a new data {@link Generator} with the specified number of elements.
     * 
     * @param numberOfElements the number of elements to generate for each data set. Must be greater
     *        than 0.
     * @return The configured generator.
     */
    public static Generator with(int numberOfElements, String... columns) {
        if (numberOfElements < 0) {
            throw new IllegalArgumentException("numberOfElements must be positive");
        }
        return new Generator(numberOfElements, columns);
    }

    private Generator(int numberOfElements, String... columns) {
        this.elements = numberOfElements;
        this.columns = columns;
        this.items = new ArrayList<List<? extends Object>>();
    }

    private void add(List<? extends Object> elements) {
        items.add(elements);
    }

    /**
     * Runs a primitive generator for {@link Primitive#BOOLEAN}, {@link Primitive#INTEGER}, and
     * {@link Primitive#FLOATING_POINT} point types. {@link BooleanGenerator} returns a list of
     * boolean values. {@link IntegerGenerator} returns a list of long values.
     * {@link FloatingPointGenerator} returns a list of double values.
     * 
     * @param options
     * @return
     */
    public Generator primitive(PrimitiveOptions options) {
        if (this.columns.length > 0 && items.size() >= this.columns.length) {
            throw new IllegalArgumentException("Cannot generate more data than available columns.");
        }
        add(switch (options.getType()) {
            case BOOLEAN -> BooleanGenerator.of(elements, options);
            case INTEGER -> IntegerGenerator.of(elements, options);
            case FLOATING_POINT -> FloatingPointGenerator.of(elements, options);
        });
        return this;
    }

    /**
     * Builds the data into the request format defined by {@link BuildOptions}
     * 
     * @param buildOption format options for the builder
     * @return the data in the requested format
     */
    public String build(BuildOptions buildOption) {
        return switch (buildOption) {
            case CSV -> new CSVBuilder().build(items, columns);
        };
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        if (columns.length > 0) {
            sb.append(Arrays.stream(columns)
                    .map(column -> String.format("%-" + TO_STRING_PADDING + "s", column))
                    .collect(Collectors.joining("|")));
            sb.append("\n");
        }
        for (int i = 0; i < elements; i++) {
            List<String> dataValues = new ArrayList<>();
            for (List<? extends Object> dataSet : items) {
                dataValues.add(dataSet.get(i).toString());
            }
            sb.append(dataValues.stream()
                    .map(value -> String.format("%-" + TO_STRING_PADDING + "s", value))
                    .collect(Collectors.joining("|")));
            sb.append("\n");
        }
        return sb.toString();
    }
}
