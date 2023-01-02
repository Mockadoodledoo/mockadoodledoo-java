package com.mockadoodledoo.builders;

import java.util.List;

public interface DataBuilder {
    public String build(List<List<? extends Object>> data, String... columns);
}
