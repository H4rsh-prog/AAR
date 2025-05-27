import java.io.File;
import java.io.FileWriter;

import static javax.swing.JOptionPane.showInputDialog;

import classes.asciiART;

public class src {
    public static void main(String[] args) {
        try {
            // asciiART aA = new asciiART(new File("asset/deathflower.png"),100,100);
            asciiART aA = new asciiART();
            // aA.ChangeImg(new File("C:\\Users\\User\\Pictures\\eerie_lake_ii_by_corrupterartai_diewrbi-pre.jpg"));
            aA.ChangeImg(new File(showInputDialog("Enter File_PATH")));
            // aA.ChangeAspect(895, 200);
            aA.ChangeAspect(Integer.parseInt(showInputDialog("Enter buffer Width")), Integer.parseInt(showInputDialog("Enter buffer Height")));
            // aA.ChangeAspect(1888, 485);
            // aA.setAsciiStr(" .'^\",:;Il!i><~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqdpbkhhao*#MW&8%B@$");
            aA.setAsciiStr(" .,:;+*?%S#@");
            String arr[] = aA.GenerateAscii("Luminosity");
            FileWriter fw = new FileWriter("./output.txt");
            for (String string : arr) {
                Thread.sleep(10);
                // fw.write("\n"+string);
                System.out.println(string);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}
