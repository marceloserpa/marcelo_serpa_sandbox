package com.marceloserpa.templaterender;

import com.marceloserpa.templaterender.template.*;

import java.util.List;

public class Main {
    static void main() {

        ReportTemplate reportTemplate = ReportTemplateFactory.create(ReportTemplateFactory.TYPE.CSV);
        reportTemplate.render("Hello Marcelo", List.of(
            new Row(List.of("item 1", "item 2", "item 3")),
            new Row(List.of("item 3", "item 5", "item 6"))
        ));


        reportTemplate = ReportTemplateFactory.create(ReportTemplateFactory.TYPE.HTML);
        reportTemplate.render("Hello Marcelo", List.of(
                new Row(List.of("item 1", "item 2", "item 3")),
                new Row(List.of("item 3", "item 5", "item 6"))
        ));

    }
}
