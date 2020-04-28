/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Jorge Videla Araya
 */
public class Excel {
    
    public void GenerarSolPed(int numerosp,String solicitante,String fechadata,Object[][] DataObjeto) {

        String nombreHoja = "SolPed LBUG.xls";
        try {
            FileInputStream inputStream = new FileInputStream(new File(nombreHoja));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Object[][] bookData = DataObjeto;
            int rowCount = sheet.getLastRowNum();
            CellStyle style = workbook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            style.setAlignment(HorizontalAlignment.CENTER);
            Cell cell2Update = sheet.getRow(1).getCell(10);//POSICION DEL NUMERO
            cell2Update.setCellValue(numerosp);
            cell2Update = sheet.getRow(7).getCell(3);//POSICION DE SOLICITADO POR
            cell2Update.setCellValue(solicitante);
            cell2Update = sheet.getRow(7).getCell(6);//POSICION DE SOLICITADO POR
            cell2Update.setCellValue(fechadata);
            cell2Update = sheet.getRow(8).getCell(7);
            sheet.getRow(8).getCell(7).setCellFormula("SUM(I13:I20)");

            for (Object[] aBook : bookData) {

                Row row = sheet.createRow(++rowCount);
                int columnCount = 1;
                Cell cell = row.createCell(columnCount);
                cell.setCellStyle(style);
                cell.setCellValue(rowCount - 11);
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    cell.setCellStyle(style);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            String nombreLibro="Solped LBUG Nº "+numerosp+".xls";
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(nombreLibro);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarConsumos()
    {
    String nombrehoja="";
    
    
    }
}
