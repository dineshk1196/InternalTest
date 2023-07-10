package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ExcelReadUtil {
    final String EXCEL_FILENAME = TestRunConfig.EXCEL_FILE_NAME;
    Logger log = LoggerFactory.getLogger(ExcelReadUtil.class);
    String filePath = null;

    public static String readExcelFile(String fileName) {
        ExcelReadUtil app = new ExcelReadUtil();
        String file = app.getFileFromResourceAsStream(fileName);
        return file;
    }

    private String getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        String inputStream = classLoader.getResource("WorkBook").getPath();
        filePath = inputStream + File.separator + fileName;

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return filePath;
        }

    }
}
