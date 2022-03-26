import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;

import java.io.FileWriter;
import java.io.IOException;

public class Extract {

    public static void main(String[] args) throws IOException {
        //Load a sample PDF document
        PdfDocument pdf = new PdfDocument("files/2020-02.pdf");

        //Create a PdfTableExtractor instance
        PdfTableExtractor extractor = new PdfTableExtractor(pdf);


        for(int z = 0; z < pdf.getPages().getCount(); z++) {
            //Extract tables from the first page
            PdfTable[] pdfTables = extractor.extractTable(z);

            //Get the first table
            PdfTable table = pdfTables[z];

            //Create a StringBuilder instance
            StringBuilder builder = new StringBuilder();

            //Loop through the rows in the current table
            for (int i = 0; i < table.getRowCount(); i++) {

                //Loop through the columns in the current table
                for (int j = 0; j < table.getColumnCount(); j++) {

                    //Extract data from the current table cell
                    String text = table.getText(i, j);

                    //Append the text to the string builder
                    builder.append(text + " ");
                }
                builder.append("\r\n");
            }
            System.out.println("PAGE " + z);
            System.out.println(builder.toString());
            System.out.println("-----------------------------\n\n");
        }


        //Write data into a .txt document
        /**
        FileWriter fw = new FileWriter("output/ExtractSpecificTableFromSpecifiedPage.txt");
        fw.write(builder.toString());
        fw.flush();
        fw.close();
         */
    }
}
