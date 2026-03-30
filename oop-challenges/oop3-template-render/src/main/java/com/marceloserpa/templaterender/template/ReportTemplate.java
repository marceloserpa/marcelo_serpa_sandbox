package com.marceloserpa.templaterender.template;

import java.util.List;

public abstract sealed class ReportTemplate permits CSVReportTemplate, HtmlReportTemplate{

    abstract String renderHeader(String text);

    abstract String renderTable(List<Row> rows);

    public void render(String text, List<Row> rows){
        var report = renderHeader(text) +
                renderTable(rows);
        System.out.println(report);
    }

}
