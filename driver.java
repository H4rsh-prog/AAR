import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showInputDialog;

import classes.asciiART;

class Resolution{
    int Width;
    int Height;
    public Resolution(int x, int y) {
        this.Width = x;
        this.Height = y;
    }
}
public class driver {
    public static void main(String[] args) {
        try {
            boolean flag1,flag2;
            asciiART aA = new asciiART();
            do { 
                try {
                    flag1=false;
                    String filePATH = showInputDialog("Enter the image file PATH : ");
                    aA.ChangeImg(new File(filePATH));
                } catch (IOException e) {
                    flag1=true;
                }
            } while (flag1);
            Resolution AR16_9X = new Resolution((600*3)+88, (150*3)+35);
            Resolution MobViewX = new Resolution((600)+88, (150*3)+35);
            Resolution AR16_9 = new Resolution((600*2)+88, (150*2)+35);
            Resolution MobView = new Resolution(((600/3)*2)+88, (150*2)+35);
            Resolution AR16_9S = new Resolution((600)+88, (150)+35);
            Resolution MobViewS = new Resolution((600/3)+88, (200)+35);
            Resolution AR16_9XS = new Resolution((600/2)+88, (150/2)+35);
            Resolution MobViewXS = new Resolution(((600/3)/2)+(88/2), (200/2)+35);
            /* NOTEPAD COMPATIBLE RESOLUTIONS */
            Resolution notepadFullScreen = new Resolution(896, 504);
            Resolution notepadSmallScreen = new Resolution(100, 56);
            char choice = '0';
            String Method = "";
            do { 
                flag2=false;
                try {
                    choice = showInputDialog("Enter which Method to use for GrayScale Value Calculation { 1-Luminosity ; 2-Lightness ; 3-Average }").charAt(0);
                } catch (Exception e) {
                    flag2=true;
                }
            } while (flag2);
            switch (choice) {
                case '2':
                    Method = "Lightness";
                    break;
                case '3':
                    Method = "Average";
                    break;
                default:
                    Method = "Luminosity";
                    break;
            }
            char choiceSize = '0';
            do {
                flag1 = false;
                try {
                    choice = showInputDialog("Please enter the Aspect Ratio { 1-MonitorDisplay(16/9) ; 2-MobileDisplay ; 3-NotepadCompatible }").charAt(0);    
                } catch (Exception e) {
                    flag1 = true;
                }
            } while (flag1);
            do { 
                flag2 = false;
                try {
                    choiceSize = showInputDialog("Enter Size { 1-FullScreen ; 2-WideScreen; 3-Small ; 4-ExtraSmall }").charAt(0);
                } catch (Exception e) {
                    flag2 = true;
                }
            } while (flag2);
            switch (choice) {
                case '2':
                    switch (choiceSize) {
                        case '1':
                            aA.ChangeAspect(MobViewX.Width,MobViewX.Height);
                            break;
                        case '3':
                            aA.ChangeAspect(MobViewS.Width,MobViewS.Height);
                            break;
                        case '4':
                            aA.ChangeAspect(MobViewXS.Width,MobViewXS.Height);
                            break;
                        default:
                            aA.ChangeAspect(MobView.Width,MobView.Height);
                            break;
                    }
                    break;
                case '3':
                    switch (choiceSize) {
                        case '1':
                            aA.ChangeAspect(notepadFullScreen.Width, notepadFullScreen.Height);
                            break;
                        case '3':
                            aA.ChangeAspect(notepadSmallScreen.Width, notepadSmallScreen.Height);
                            break;
                        case '4':
                            aA.ChangeAspect(notepadSmallScreen.Width, notepadSmallScreen.Height);
                            break;
                        default:
                            aA.ChangeAspect(notepadFullScreen.Width, notepadFullScreen.Height);
                            break;
                    }
                    break;
                default:    
                    switch (choiceSize) {
                        case '1':
                            aA.ChangeAspect(AR16_9X.Width,AR16_9X.Height);
                            break;
                        case '3':
                            aA.ChangeAspect(AR16_9S.Width,AR16_9S.Height);
                            break;
                        case '4':
                            aA.ChangeAspect(AR16_9XS.Width,AR16_9XS.Height);
                            break;
                        default:
                            aA.ChangeAspect(AR16_9.Width,AR16_9.Height);
                            break;
                    }
                    break;
            }
            aA.setAsciiStr(aA.AsciiArr);
            String[] AsciiArr = aA.GenerateAscii(Method);
            for(String s : AsciiArr){
                System.out.println(s);
            }
            //``````````````````````````````````````````````````````````//
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        // } catch (AWTException ex) {
        //     ex.printStackTrace();
        // } catch (InterruptedException ex) {
        //     ex.printStackTrace();
        }
    }
}
