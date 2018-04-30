
import java.util.Calendar;
import javax.swing.filechooser.FileSystemView;
import scantool.Excel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bluemond
 */
public class ExcelWriteTest {

    public static void main(String[] args) {
        Excel x = new Excel(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "\\test.xlsx");
        Calendar now = Calendar.getInstance();
        long a = now.getTimeInMillis();
//        System.out.println(a);
        int n = 1000;
        for (int i = 0; i < n; i++) {
            x.writeLine("10000"+i);
        }
        now = Calendar.getInstance();
        long b = now.getTimeInMillis();
//        System.out.println(b);
        long c = b-a;
        
        System.out.print(c);
    }
}
