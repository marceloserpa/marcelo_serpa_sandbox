package com.marceloserpa.templaterender.template;

import java.util.List;

public abstract class ReportTemplate {

    abstract String renderHeader(String text);

    abstract String renderTable(List<Row> rows);

    public void render(String text, List<Row> rows){
        var report = new StringBuilder().append(renderHeader(text))
                .append(renderTable(rows)).toString();
        System.out.println(report);
    }

}
