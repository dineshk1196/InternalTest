package utils.PDFutils;

import de.redsix.pdfcompare.PdfComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ComparePDFVisually {
    public static Logger log = LoggerFactory.getLogger(ComparePDFVisually.class);

    boolean isEquals = false;

    public void ComparePDFs(String file1, String file2) throws Exception {
        try{
            File f1 = new File(file1);
            File f2 = new File(file2);
         try {
                 isEquals = new PdfComparator(f1, f2).compare().writeTo("C:\\Users\\phaneendrapo\\Downloads\\ComparePDFResult.pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!isEquals) {
                throw new Exception("Differences Found in provided files "+file1+ "and "+file2);
            }
            if (isEquals) {
                log.info("Differences not found");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}