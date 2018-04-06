/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.1
4
4
 */
package scantool;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Bluemond
 */
public class Excel {

    /**
     *
     * @param args
     *
     * @throws AWTException
     *
     */
    public XSSFWorkbook wb = null;
    public XSSFSheet sheet = null;
    public int quantity;
    String addr = null;

    public Excel(String addrin) {
        addr = addrin;

        File file = new File(addrin);
        if (!file.exists()) {
            //第一步创建workbook  
            wb = new XSSFWorkbook();

            //第二步创建sheet  
            sheet = wb.createSheet("Sheet");
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("条码信息:");

            POIXMLProperties xmlProps = wb.getProperties();
            POIXMLProperties.CoreProperties coreProps = xmlProps.getCoreProperties();
            coreProps.setCreator("Bluemond From BHSF");
            //第三步将生成excel文件保存到指定路径下  
            try {
                FileOutputStream fout = new FileOutputStream(addr);
                wb.write(fout);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Excel文件生成成功...");

        }
        try {
            // 读取Excel  
            InputStream is = new FileInputStream(addr);
            wb = new XSSFWorkbook(is);

            sheet = wb.getSheetAt(0);
            quantity = sheet.getLastRowNum();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String line) {
        //在原来的基础上新建一行并输入数据
        XSSFRow row = sheet.createRow(quantity + 1);
        row.createCell(0).setCellValue(line);
        quantity++;
        //保存
        try {
            FileOutputStream fout = new FileOutputStream(addr);
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getCellValue(Cell cell) {
        Object result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    long num = (long) cell.getNumericCellValue();
                    result = num;
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    break;
            }
        }
        return result.toString();
    }

}
