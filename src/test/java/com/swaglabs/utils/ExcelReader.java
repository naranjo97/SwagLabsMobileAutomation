package com.swaglabs.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    /**
     * Lee una hoja específica de un archivo Excel y retorna los datos en una estructura de Lista de Mapas.
     * @param filepath Ruta física o relativa del archivo Excel.
     * @param sheetName Nombre de la pestaña (hoja) que se desea leer.
     * @return List<Map<String, String>> con los datos de la hoja.
     */
    public static List<Map<String, String>> data(String filepath, String sheetName) {
        List<Map<String, String>> excelData = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filepath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("La hoja '" + sheetName + "' no existe en el archivo: " + filepath);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return excelData; // Hoja vacía
            }

            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCols = headerRow.getPhysicalNumberOfCells();

            // Recorrer a partir de la fila 1 (omitir cabecera)
            for (int i = 1; i < totalRows; i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                Map<String, String> rowMap = new HashMap<>();

                for (int j = 0; j < totalCols; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell currentCell = currentRow.getCell(j);

                    String columnName = (headerCell != null) ? headerCell.getStringCellValue().trim() : "Columna_" + j;
                    String cellValue = "";

                    if (currentCell != null) {
                        // Forzar a tratar la celda como String para evitar errores con números (como tarjetas o códigos postales)
                        currentCell.setCellType(CellType.STRING);
                        cellValue = currentCell.getStringCellValue().trim();
                    }

                    rowMap.put(columnName, cellValue);
                }
                excelData.add(rowMap);
            }

        } catch (IOException e) {
            System.err.println("Error crítico al intentar leer el archivo Excel: " + e.getMessage());
            e.printStackTrace();
        }

        return excelData;
    }
}