package utils;

// Import for reading file input stream (reading the Excel file)
import java.io.FileInputStream;
import java.io.IOException;

// Apache POI imports (used to work with Excel files)
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtils
 * ----------
 * This class is responsible for reading data from an Excel file
 * and converting it into a format that TestNG DataProvider can use.
 */
public class ExcelUtils {

    /**
     * getExcelData()
     * ---------------
     * Reads data from an Excel sheet and returns it as a 2D Object array.
     * This is required by TestNG @DataProvider.
     *
     * @param filePath  - Path to Excel file
     * @param sheetName - Name of the sheet inside Excel
     * @return Object[][] - Data in rows and columns
     */
    public static Object[][] getExcelData(String filePath, String sheetName) {

        // This will hold all the data we read from Excel
        Object[][] data = null;

        try (
            // Open the Excel file
            FileInputStream fis = new FileInputStream(filePath);

            // Create workbook object from the file
            XSSFWorkbook workbook = new XSSFWorkbook(fis)
        ) {

            // Get the specific sheet by name
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // Total number of rows (including header)
            int rowCount = sheet.getPhysicalNumberOfRows();

            // Total number of columns (based on first row)
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            /**
             * IMPORTANT:
             * We subtract 1 from rowCount because:
             * - Row 0 = header (username, password, expected)
             * - Actual data starts from row 1
             */
            data = new Object[rowCount - 1][colCount];

            /**
             * DataFormatter is used to safely convert cell values to String
             * (handles numbers, text, etc. without crashing)
             */
            DataFormatter formatter = new DataFormatter();

            // Loop through rows (starting from 1 to skip header)
            for (int i = 1; i < rowCount; i++) {

                // Get current row
                Row row = sheet.getRow(i);

                // Loop through each column in the row
                for (int j = 0; j < colCount; j++) {

                    // Get the cell at row i, column j
                    Cell cell = row.getCell(j);

                    // Convert cell value to String and store in array
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }

        } catch (IOException e) {
            // Print error if file not found or cannot be read
            e.printStackTrace();
        }

        // Return the final data array
        return data;
    }
}