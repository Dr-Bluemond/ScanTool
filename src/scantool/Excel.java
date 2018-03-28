/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scantool;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
    public Vector<String> code = new Vector<String>();
    public XSSFWorkbook wb = null;

    public Excel(String addr) {
        File file = new File(addr);
        if (!file.exists()) {
            //第一步创建workbook  
            XSSFWorkbook wb = new XSSFWorkbook();

            //第二步创建sheet  
            XSSFSheet sheet = wb.createSheet("Sheet");

            //第三步创建行row:添加表头0行  
            XSSFRow row = sheet.createRow(0);
            XSSFCellStyle style = wb.createCellStyle();
            //style.setAlignment(XSSFCellStyle.ALIGN_CENTER);  //居中  
/*
            //第四步创建单元格  
            XSSFCell cell = row.createCell(0); //第一个单元格  
            cell.setCellValue("姓名");
            cell.setCellStyle(style);

            cell = row.createCell(1);         //第二个单元格     
            cell.setCellValue("年龄");
            cell.setCellStyle(style);

            //第五步插入数据  
            for (int i = 0; i < 5; i++) {
                //创建行  
                row = sheet.createRow(i + 1);
                //创建单元格并且添加数据  
                row.createCell(0).setCellValue("aa" + i);
                row.createCell(1).setCellValue(i);
            }
*/
            

            //第六步将生成excel文件保存到指定路径下  
            try {
                FileOutputStream fout = new FileOutputStream(addr);
                wb.write(fout);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Excel文件生成成功...");

            try {
                // 读取Excel  
                InputStream is = new FileInputStream(addr);
                wb = new XSSFWorkbook(is);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void readExcel(String addr) {

        try {
            // 获取sheet数目  
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            int lastRowNum = sheet.getLastRowNum();
            // 循环读取  
            for (int i = 0; i <= lastRowNum; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    // 获取每一列的值

                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        String value = getCellValue(cell);
                        if (!value.equals("")) {
                            code.add(value);

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeExcel(String a) {

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
