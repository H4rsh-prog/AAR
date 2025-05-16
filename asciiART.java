package classes;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class asciiART {
    final char LEGACY_STR[] = " `.-':_,^=;><+!rc*/z?sLTv)J7(|Fi{C}fI31tlu[neoZ5Yxjya]2ESwqkP6h9d4VpOGbUAKXHm8RD#$Bg0MNWQ%&@".toCharArray(); //len - 92
    final public static char ExtAsciiArr[] = " .-:_,^=;><+!rctunoxjywqk6h4OGb8#$BQ%&@".toCharArray(); //len - 39
    final public static char AsciiArr[] = " .:`,'^=-;<+r*?}fUA#%@".toCharArray(); //len-22
    final public static char CompactAsciiArr[] = " .:=-+*#%@".toCharArray(); //len-10
    static char ChosenStr[];
    static int strLen = 22;
    private int Width = 0;
    private int Height = 0;
    
    BufferedImage buffImg;
    public asciiART(){
        this.ChosenStr = this.AsciiArr;
    }
    public asciiART(File imgFile) throws IOException {
        this.buffImg = ImageIO.read(imgFile);
        this.Width = buffImg.getWidth();
        this.Height = buffImg.getHeight();
        this.ChosenStr = this.AsciiArr;
        String[] strArr = GenerateAscii("Luminosity");
        for(String str : strArr){
            System.out.println(str);
        }
    }
    public void setAsciiStr(char strArr[]){
        this.ChosenStr = strArr;
    }
    public String[] GenerateAscii(String Method){
        String[] strArr = new String[this.Height];
        char[] colStr = new char[this.Width];
        for (int y=0;y<this.Height;y++) {
            for(int x=0;x<this.Width;x++){
                Color ImgRGB = new Color(buffImg.getRGB(x, y));
                int RedVal = ImgRGB.getRed();
                int GreenVal = ImgRGB.getGreen();
                int BlueVal = ImgRGB.getBlue();
                float Gray = 0;
                switch (Method) {
                    case "Luminosity":
                        Gray = (int)((RedVal*0.21)+(GreenVal*0.72)+(BlueVal*0.07));
                        break;
                    case "Lightness":
                        int tempMax = Math.max(RedVal, GreenVal);
                        int tempMin = Math.min(RedVal, GreenVal);
                        int tempSum = Math.max(tempMax, BlueVal)+Math.min(tempMin, BlueVal);
                        Gray = tempSum/2;
                        break;
                    case "Average":
                        Gray = ((RedVal+GreenVal+BlueVal)/3);
                        break;
                }
                float PrcntGrayScale = (Gray/255)*100;
                float PrcntCharArr = (PrcntGrayScale/100)*strLen;
                int charIndex = (int) PrcntCharArr;
                if(charIndex==strLen){
                    charIndex = strLen-1;
                }
                colStr[x] = this.ChosenStr[charIndex];
            }
            
            strArr[y] = String.copyValueOf(colStr);
        }
        return strArr;
    }
    public void ChangeImg(File imgFile) throws IOException{
        this.buffImg = ImageIO.read(imgFile);
        this.Width = buffImg.getWidth();
        this.Height = buffImg.getHeight();
    }
    public void ChangeAspect(int Width, int Height) throws IOException{
        BufferedImage NewRatio = new BufferedImage(Width, Height, buffImg.getType());
        double ScaleX = (double) Width/ (double) this.buffImg.getWidth();
        double ScaleY = (double) Height/ (double) this.buffImg.getHeight();
        AffineTransform afTrans = new AffineTransform();
        afTrans.scale(ScaleX,ScaleY);
        AffineTransformOp afTransOp = new AffineTransformOp(afTrans, AffineTransformOp.TYPE_BILINEAR);
        NewRatio = afTransOp.filter(this.buffImg, NewRatio);
        this.buffImg = NewRatio;
        this.Width = buffImg.getWidth();
        this.Height = buffImg.getHeight();
    }
}

