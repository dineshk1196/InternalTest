package utils.excelOperations;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import model.DataSheetType;
import model.User;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestRunConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelOperations<T> {
    private static final String EXCEL_FILENAME = TestRunConfig.EXCEL_FILE_NAME;
    public static Logger log = LoggerFactory.getLogger(ExcelOperations.class);
    private static String filePath = null;

    public List<T> get(DataSheetType dataSheetType) {
        filePath = this.getClass().getClassLoader().getResource("WorkBook").getPath();
        filePath = filePath + File.separator + EXCEL_FILENAME;
        try {
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                    .sheetName(dataSheetType.getSheetName()).trimCellValue(true).preferNullOverDefault(false).build();
            List<T> tList = new ArrayList<>();
            ZipSecureFile.setMinInflateRatio(0);
        switch (dataSheetType) {
            case USERS:
                tList = (List<T>) Poiji.fromExcel(new File(filePath), User.class, options);
                break;
        }
            return tList;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

}
