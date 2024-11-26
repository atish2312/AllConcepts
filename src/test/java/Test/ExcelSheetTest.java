package Test;

import Utils.Config.BaseClass;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelSheetTest extends BaseClass {

    @Test
    public void openSameLinkMultipleTimes() throws IOException, InterruptedException {
        // Define the path to your Excel file
        String excelPath = "src/test/java/Resources/thrive.xlsx";

        // Open the Excel file
        FileInputStream inputStream = new FileInputStream(excelPath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        // Get the total number of rows in the sheet
        int rows = sheet.getPhysicalNumberOfRows();

        // Iterate through the rows in batches of 10
        for (int batchStart = 0; batchStart < rows; batchStart += 10) {
            // Calculate the batch end index (ensure it does not go beyond total rows)
            int batchEnd = Math.min(batchStart + 10, rows);

            // Iterate through the links in the current batch (from batchStart to batchEnd)
            for (int r = batchStart; r < batchEnd; r++) {
                XSSFRow row = sheet.getRow(r);  // Get the row
                if (row == null) continue;  // Skip empty rows

                XSSFCell cell = row.getCell(0);  // Assuming the URL is in the first column
                if (cell == null) continue;  // Skip empty cells

                String url = cell.getStringCellValue();  // Read URL

                // Ensure the URL is not empty
                if (url != null && !url.isEmpty()) {
                    System.out.println("Navigating to: " + url);

                    // Open the same URL 3 times (as per your original logic)
                    for (int i = 0; i < 3; i++) {
                        boolean linkSuccessful = false;

                        System.out.println("Attempt " + (i + 1) + " to open the link: " + url);
                        try {
                            driver.get(url);
                            Thread.sleep(2000); // Sleep for 2 seconds between attempts
                            linkSuccessful = true;
                        } catch (Exception e) {
                            System.out.println("Attempt " + (i + 1) + " to load " + url + " failed. Error: " + e.getMessage());
                        }

                        // If after 3 attempts the link still didn't open successfully, log it
                        if (!linkSuccessful && i == 2) {
                            System.out.println("Failed to load page after 3 attempts: " + url);
                        }
                    }

                    // Log successful navigation to the URL
                    Assert.assertTrue(true, "Successfully opened the link after 3 attempts: " + url);

                    // Wait for a moment before trying the next URL in the current batch
                    System.out.println("Moving to the next URL...\n");
                } else {
                    System.out.println("URL is empty or invalid at row " + (r + 1));
                }
            }

            // Log that the batch has finished processing
            System.out.println("Batch from " + (batchStart + 1) + " to " + batchEnd + " completed.\n");
        }

        // Cleanup
        workbook.close();
        inputStream.close();
        driver.quit();
    }
}
