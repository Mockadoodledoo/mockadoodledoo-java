package com.mockadoodledoo.builders;

import java.util.ArrayList;
import java.util.List;

public class CSVBuilder implements DataBuilder {

    @Override
    public String build(List<List<? extends Object>> data, String... columns) {
        var sb = new StringBuilder();
        if (columns.length > 0) {
            sb.append(String.join(",", columns));
            sb.append("\n");
        }
        for (int i = 0; i < data.get(0).size(); i++) {
            List<String> dataValues = new ArrayList<>();
            for (List<? extends Object> dataSet : data) {
                dataValues.add(dataSet.get(i).toString());
            }
            sb.append(String.join(",", dataValues));
            sb.append("\n");
        }
        return sb.toString();
    }

}
