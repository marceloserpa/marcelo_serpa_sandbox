package com.marceloserpa.templaterender.template;

import java.util.List;

public class CSVReportTemplate extends ReportTemplate{
    @Override
    String renderHeader(String text) {
        return "== %s ==;\n".formatted(text);
    }

    @Override
    String renderTable(List<Row> rows) {
        StringBuilder sb = new StringBuilder();
        for(Row row : rows) {
            for(String col : row.columns()) {
                sb.append(col).append(";");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
