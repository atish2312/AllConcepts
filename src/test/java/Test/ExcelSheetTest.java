package Test;

import Utils.Config.BaseClass;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelSheetTest extends BaseClass {

    @Test
    public void getExcelSheetData() throws FileNotFoundException, IOException, InterruptedException {
        // Define the path to your Excel file
        String excelPath = "src/test/java/Resources/TISH.xlsx";
        ArrayList<String> urls = new ArrayList<>();  // List to store URLs from the Excel sheet

        // Open Excel file
        FileInputStream inputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rows = sheet.getPhysicalNumberOfRows(); // Get actual number of rows
        int cols = sheet.getRow(0).getPhysicalNumberOfCells(); // Get the number of cells in the first row

        // Read data from the sheet and store URLs in the ArrayList
        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.getRow(r);

            if (row != null) {
                for (int c = 0; c < cols; c++) {
                    XSSFCell cell = row.getCell(c);

                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                String url = cell.getStringCellValue();
                                System.out.println("STRING: " + url);
                                if (url != null && !url.isEmpty()) {
                                    urls.add(url);  // Add the URL to the list
                                }
                                break;
                            case NUMERIC:
                                System.out.println("NUMERIC: " + cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                System.out.println("BOOLEAN: " + cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                System.out.println("FORMULA: " + cell.getCellFormula());
                                break;
                            default:
                                System.out.println("OTHER: " + cell.toString());
                        }
                    }
                }
            }
        }

        // Initialize WebDriver (ChromeDriver in this case)
        WebDriver driver = new ChromeDriver();  // Make sure ChromeDriver is in your PATH

        // Open each URL one by one
        for (String url : urls) {
            if (url != null && !url.isEmpty()) {
                System.out.println("Navigating to: " + url);
                driver.get(url);  // Navigate to the URL
                    Thread.sleep(2000);  // Optional: Wait for 2 seconds before moving to the next URL (adjust as needed)


            }
        }

        // Close the browser after all URLs have been visited
        driver.quit();  // This closes the browser completely

        // Close Excel resources
        workbook.close();  // Close the workbook
        inputStream.close();  // Close the input stream
    }
}
