package com.mockadoodledoo;

import com.mockadoodledoo.generators.options.PrimitiveOptions;
import com.mockadoodledoo.generators.types.Primitive;

public class App {

    public static void main(String[] args) {
        var gen =
                Generator.of(50).primitive(new PrimitiveOptions(Primitive.INTEGER, -100000, 10000))
                        .primitive(new PrimitiveOptions(Primitive.BOOLEAN))
                        .primitive(
                                new PrimitiveOptions(Primitive.FLOATING_POINT, -1000.245, 1000.254))
                        .primitive(new PrimitiveOptions(Primitive.FLOATING_POINT));
        var gen2 =
                Generator.of(50, "Test", "Test2").primitive(new PrimitiveOptions(Primitive.BOOLEAN))
                        .primitive(new PrimitiveOptions(Primitive.BOOLEAN));
        System.out.println(gen);
        System.out.println(gen2);
        System.out.println(gen2.build(BuildOptions.CSV));
    }
}
