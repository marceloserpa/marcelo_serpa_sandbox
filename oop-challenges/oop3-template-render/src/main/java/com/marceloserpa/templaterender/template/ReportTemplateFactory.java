package com.marceloserpa.templaterender.template;

public class ReportTemplateFactory {

    public enum TYPE { HTML, CSV }

    public static ReportTemplate create(TYPE reportType) {
        return switch (reportType) {
            case HTML -> new HtmlReportTemplate();
            case CSV -> new CSVReportTemplate();
        };
    }

}
