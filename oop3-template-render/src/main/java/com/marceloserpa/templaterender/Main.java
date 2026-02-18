package com.marceloserpa.templaterender;

import com.marceloserpa.templaterender.template.CSVReportTemplate;
import com.marceloserpa.templaterender.template.ReportTemplate;
import com.marceloserpa.templaterender.template.Row;

import java.util.List;

public class Main {
    static void main() {

        ReportTemplate reportTemplate = new CSVReportTemplate();
        reportTemplate.render("Hello Marcelo", List.of(
            new Row(List.of("item 1", "item 2", "item 3")),
            new Row(List.of("item 3", "item 5", "item 6"))
        ));

    }
}
