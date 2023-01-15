package com.mockadoodledoo.builders;

import java.util.List;

public interface DataBuilder {
    /**
     * Build the data set into the {@Link DataBuilder} implementation format
     * 
     * @param data
     * @param columns
     * @return
     */
    public String build(List<List<? extends Object>> data, String... columns);
}
