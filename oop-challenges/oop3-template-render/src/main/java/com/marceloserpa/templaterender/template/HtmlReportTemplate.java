package com.marceloserpa.templaterender.template;

import java.util.List;

public final class HtmlReportTemplate extends ReportTemplate{
    @Override
    String renderHeader(String text) {
        return "<head>%s</head>".formatted(text);
    }

    @Override
    String renderTable(List<Row> rows) {

        StringBuilder sb = new StringBuilder("<table>");
        for(Row row : rows) {
            sb.append("<tr>");
            for (String col: row.columns()) {
                sb.append("<td>").append(col).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
