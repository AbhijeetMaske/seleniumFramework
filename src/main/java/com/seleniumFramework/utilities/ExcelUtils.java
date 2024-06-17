package com.seleniumFramework.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelUtils {

	private static final Logger logger = LogManager.getLogger(ExcelUtils.class);
    private String filePath;
    private Workbook workbook;

    /********************************************************************************************
     * Constructor to initialize the ExcelUtility with the file path.
     *
     * @param filePath the path of the Excel file to be read or written
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public ExcelUtils(String filePath) {
        this.filePath = filePath;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            this.workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
        	logger.error("Failed to initialize ExcelUtility: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /********************************************************************************************
     * Reads data from the specified sheet in the Excel file.
     *
     * @param sheetName the name of the sheet from which data is to be read
     * @return a list of rows, where each row is represented as a list of strings
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public List<List<String>> readExcelData(String sheetName) {
        List<List<String>> excelData = new ArrayList<>();

        try {
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

        } catch (Exception e) {
        	logger.error("Failed to read Excel data: " + e.getMessage());
            e.printStackTrace();
        }
        return excelData;
    }

    /********************************************************************************************
     * Writes data to the specified sheet in the Excel file.If the sheet does not exist, it creates a new one.
     *
     * @param sheetName the name of the sheet to which data is to be written
     * @param data      the data to be written, represented as a list of rows, where each row is a list of strings
     * 
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public void writeExcelData(String sheetName, List<List<String>> data) {
        try {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            int rowCount = 0;
            for (List<String> rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int cellCount = 0;
                for (String cellData : rowData) {
                    Cell cell = row.createCell(cellCount++);
                    cell.setCellValue(cellData);
                }
            }

        } catch (Exception e) {
        	logger.error("Failed to write Excel data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /********************************************************************************************
     * Saves changes made to the workbook back to the file.
     *  
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    
    public void saveChanges() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
        	logger.error("Failed to save changes to Excel file: " + e.getMessage());
            e.printStackTrace();
        }
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
    private String getCellValueAsString(Cell cell) {
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

    public static void main(String[] args) {
        ExcelUtils excelUtility = new ExcelUtils("path/to/excel/file.xlsx");

        // Reading data
        List<List<String>> data = excelUtility.readExcelData("Sheet1");
        for (List<String> row : data) {
            System.out.println(row);
        }

        // Writing data
        List<List<String>> newData = new ArrayList<>();
        newData.add(List.of("Header1", "Header2", "Header3"));
        newData.add(List.of("Data1", "Data2", "Data3"));
        excelUtility.writeExcelData("Sheet2", newData);

        // Saving changes
        excelUtility.saveChanges();
    }
}