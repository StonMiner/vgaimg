import java.io.*;
import java.nio.*;
import java.awt.*;
import javax.swing.*;

class v{
    public static void main(String[] args) {
        new viewer();
    }
}

public class viewer extends JFrame {
    int maxWidth=640;
    int maxHeight=480;



    byte[] pixeldata = new byte[4096000];
    /*
    bool black=false;
    bool blue=false;
    bool green=false;
    bool cyan=false;
    bool red=false;
    bool purple=false;
    bool brown=false;
    bool darkgray=false;
    bool lightblue=false;
    bool lightgreen=false;
    bool lightcyan=false;
    bool lightred=false;
    bool lightpurple=false;
    bool yellow=false;
    bool white=false;
    //special
    bool NEWLINE=false;
    */

    viewer(){
        launch();
        parseData();
    }


    void launch(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(maxWidth,maxHeight);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void parseData(){
        try (FileInputStream fio = new FileInputStream("new.vga")) {
            int i;
            int i2=0; //pixel index
            while ((i = fio.read()) != -1) {
                /*
                if(i==0x00){
                    pixeldata[i2] = 0x00;
                }else if(i==0x01){
                    pixeldata[i2] = 0x01;
                }else if(i==0x02){
                    pixeldata[i2] = 0x02;
                }else if(i==0x03){
                    pixeldata[i2] = 0x03;
                }else if(i==0x04){
                    pixeldata[i2] = 0x04;
                }else if(i==0x05){
                    pixeldata[i2] = 0x05;
                }else if(i==0x06){
                    pixeldata[i2] = 0x06;
                }else if(i==0x07){
                    pixeldata[i2] = 0x07;
                }else if(i==0x08){
                    pixeldata[i2] = 0x08;
                }else if(i==0x09){
                    pixeldata[i2] = 0x09;
                }else if(i==0x10){
                    pixeldata[i2] = 0x10;
                }else if(i==0x11){
                    pixeldata[i2] = 0x11;
                }else if(i==0x12){
                    pixeldata[i2] = 0x12;
                }else if(i==0x13){
                    pixeldata[i2] = 0x13;
                }else if(i==0x14){
                    pixeldata[i2] = 0x14;
                }else if(i==0x15){
                    pixeldata[i2] = 0x15;
                }else if(i==0x0A){
                    i2=0; //set index 0
                    for(int i3=0;i<640;i++){
                        //pixeldata[i3]=0xFF; //clear buffer
                    }
                }
                */


                pixeldata[i2] = (byte)i;
                i2++;
            }
            fio.close();
            System.out.println(); // final newline
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        render(g2d);
    }

    private void render(Graphics2D g2d) {
        int x=0;
        int y=0;
        int i=0;

        for(x=0;x<maxWidth;x++){
        byte value = pixeldata[i++];
            switch (value) {
                case 0x00: g2d.setColor(new Color(0,0,0)); break;
                case 0x01: g2d.setColor(new Color(0,0,170)); break;
                case 0x02: g2d.setColor(new Color(0,170,0)); break;
                case 0x03: g2d.setColor(new Color(0,170,170)); break;
                case 0x04: g2d.setColor(new Color(170,0,0)); break;
                case 0x05: g2d.setColor(new Color(170,0,170)); break;
                case 0x06: g2d.setColor(new Color(170,85,0)); break;
                case 0x07: g2d.setColor(new Color(170,170,170)); break;
                case 0x08: g2d.setColor(new Color(85,85,85)); break;
                case 0x09: g2d.setColor(new Color(85,85,255)); break;
                case 0x10: g2d.setColor(new Color(85,255,85)); break;
                case 0x11: g2d.setColor(Color.decode("#55FFFF")); break;
                case 0x12: g2d.setColor(new Color(255,85,85)); break;
                case 0x13: g2d.setColor(new Color(255,85,255)); break;
                case 0x14: g2d.setColor(new Color(255,255,85)); break;
                case 0x15: g2d.setColor(Color.WHITE); break;
                case (byte)0xFF:
                    System.out.printf("filler byte found, skipping\n");
                    continue;
            }

            if(x==639){
                x=0;
                y++;
            }
            if(y==479){
                x=640;
                y=480;
                finish();
            }

            g2d.fillRect(x,y,1,1);
        }


    }

    void finish(){
        System.out.println("Finished");
    }

}
