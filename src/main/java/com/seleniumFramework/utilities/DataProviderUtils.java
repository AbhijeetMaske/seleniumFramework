package com.seleniumFramework.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataProviderUtils {
	private static final Logger logger = LogManager.getLogger(DataProviderUtils.class);

	/********************************************************************************************
     * Reads data from the specified Excel file and sheet, and provides it to TestNG tests.
     * 
     * @param filePath  the path of the Excel file to read data from
     * @param sheetName the name of the sheet from which data is to be read
     * @return a 2D array of objects representing the data to be provided to the tests
     * @throws IOException if there is an error reading the file
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
	
    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        List<List<String>> excelData = new ArrayList<>();
        Workbook workbook = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in " + filePath);
            }

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<String> rowData = new ArrayList<>();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowData.add(getCellValueAsString(cell));
                }
                excelData.add(rowData);
            }
        } catch (IOException e) {
        	logger.error("Exception while reading Excel file: " + e.getMessage());
            throw e;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }

        // Convert List<List<String>> to Object[][]
        Object[][] dataArray = new Object[excelData.size()][];
        for (int i = 0; i < excelData.size(); i++) {
            List<String> row = excelData.get(i);
            dataArray[i] = row.toArray(new Object[0]);
        }
        return dataArray;
    }

    /********************************************************************************************
     * Converts the cell value to a string based on its type.
     * 
     * @param cell the cell whose value is to be converted
     * @return the cell value as a string
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    
    private static String getCellValueAsString(Cell cell) {
        String cellValue;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    cellValue = Double.toString(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                cellValue = "";
                break;
            default:
                cellValue = "";
        }
        return cellValue;
    }

    /********************************************************************************************
     * DataProvider method to provide data from an Excel file to TestNG tests.
     * 
     * @return a 2D array of objects representing the data to be provided to the tests
     * @throws IOException if there is an error reading the file
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    @DataProvider(name = "excelDataProvider")
    public Object[][] excelDataProvider() throws IOException {
        String filePath = "path/to/excel/file.xlsx";
        String sheetName = "Sheet1";
        return getExcelData(filePath, sheetName);
    }

    public static void main(String[] args) {
        try {
            Object[][] data = getExcelData("path/to/excel/file.xlsx", "Sheet1");
            for (Object[] row : data) {
                for (Object cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
        	logger.error("Exception in main method: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
