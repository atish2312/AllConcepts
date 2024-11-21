package Test;

import Utils.Config.BaseClass;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExceTest1 extends BaseClass {


    public static void getData() throws IOException {
        // Path to the Excel file
        String getexcel = "src/test/java/Resources/TISH.xlsx";
        int[] targetColoumn = {1,2};

        // Open the Excel file
        FileInputStream file = new FileInputStream(getexcel);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet2");

        // Get number of rows and columns
        int rows = sheet.getPhysicalNumberOfRows(); // Total rows in the sheet
        int columns = sheet.getRow(0).getPhysicalNumberOfCells(); // Columns in the first row

        System.out.println("Number of Rows: " + rows);
        System.out.println("Number of Columns: " + columns);
        System.out.println("\nReading Excel Data:\n");

        // Iterate through rows
        for (int r = 1; r < rows; r++) {
            XSSFRow row = sheet.getRow(r);

            if (row != null) {
                // Iterate through columns in the current row
             //   for (int c = 0; c < columns; c++) {t
                for(int colomnindex :targetColoumn ){
                    XSSFCell cell = row.getCell(colomnindex);

                    if (cell != null) {
                        // Process cell based on its type
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t"); // Print string values
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t"); // Print numeric values
                                break;
                            case BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t"); // Print boolean values
                                break;
                            case FORMULA:
                                System.out.print(cell.getCellFormula() + "\t"); // Print formula (if any)
                                break;
                            default:
                                System.out.print("UNKNOWN\t"); // For other types or blank cells
                        }
                    } else {
                        System.out.print("NULL\t"); // Handle null cells
                    }
                }
                System.out.println(); // Move to the next row
            }
        }

        // Close resources
        workbook.close();
        file.close();
    }
}
