import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {

        PDDocument pd = PDDocument.load(new File("files/2021-12.pdf"));

        ObjectExtractor oe = new ObjectExtractor(pd);

        SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm(); // Tabula algo.

        Page page = oe.extract(1); // extract only the first page

        List<Table> table = sea.extract(page);

        for(Table tables: table) {
            List<List<RectangularTextContainer>> rows = tables.getRows();

            for(int i=0; i<rows.size(); i++) {

                List<RectangularTextContainer> cells = rows.get(i);

                for(int j=0; j<cells.size(); j++) {
                    System.out.print(cells.get(j).getText()+"|");
                }

                System.out.println();
            }
        }




    }
}
