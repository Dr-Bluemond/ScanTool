/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bluemond
 */
public class NewClass {
    public static void main(String[] agrs) throws InterruptedException{
        NewClass o = new NewClass();
        o.print(0);
    }
    private void print(int line) throws InterruptedException{
        System.out.println(line);
        line = line+1;
        if(line>100000){
        }else{
        print(line);
        }
    }
}
