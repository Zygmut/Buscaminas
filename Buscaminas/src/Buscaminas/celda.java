/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscaminas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Zygmut
 */
public class celda {
    
    public String[] posiblidades = {"0", "1", "2", "3", "bomba", "yeer"};
    public static final String cover = "sprite/cover.png";
    public static final String mark = "sprite/mark.png";
    public static final String bomba = "sprite/bomba.png";
    public static final String zero = "sprite/0.png";
    public static final String one = "sprite/1.png";
    public static final String two = "sprite/2.png";
    public static final String three = "sprite/3.png";
    public String[][] celda = new String[9][9];
    String id;
    BufferedImage img;

    /*
     * Constructor
     */
    public celda(String s) {
        id = s.split("/")[1].split(".p")[0];
        //this.img = ImageIO.read(new File(s));
        celda[1][2] = posiblidades[4];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (true ) {
                    celda[i][j] += 1;
                }
            }
        }
    }
}
