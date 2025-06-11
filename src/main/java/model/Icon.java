package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * The class Icon
 *
 */
public class Icon {
    private BufferedImage icon0;
    private BufferedImage icon1;
    private BufferedImage icon2;
    private BufferedImage icon3;
    private BufferedImage icon4;
    private BufferedImage icon5;
    private BufferedImage icon6;
    private BufferedImage icon7;
    private BufferedImage icon8;
    private BufferedImage icon9;


    /**
     * The constructor for the Icon class
     * Throws a generic Exception if it fails to locate the image files
     *
     * @throws Exception
     */
    public Icon() {
        String a = File.separator;
        try {
            icon0 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"edit.png"));
            icon1 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"home.png"));
            icon2 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"like.png"));
            icon3 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"map.png"));
            icon4 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"music.png"));
            icon5 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"muted.png"));
            icon6 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"smartphone.png"));
            icon7 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"stopwatch.png"));
            icon8 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"user.png"));
            icon9 = ImageIO.read(new File(".."+a+".."+a+"icons"+a+"users.png"));
        }
        catch(IOException e) {
        System.out.println("No images found");}
    }

    /**
     * Takes a T0do as input, gets its Icon value and returns its corresponding image as an ImageIcon
     *
     * @param todo
     * @return ImageIcon
     */
    public ImageIcon getIcon(Todo todo){
        switch(todo.getIcon()) {
            case 0:
                return new ImageIcon(icon0);
            case 1:
                return new ImageIcon(icon1);
            case 2:
                return new ImageIcon(icon2);
            case 3:
                return new ImageIcon(icon3);
            case 4:
                return new ImageIcon(icon4);
            case 5:
                return new ImageIcon(icon5);
            case 6:
                return new ImageIcon(icon6);
            case 7:
                return new ImageIcon(icon7);
            case 8:
                return new ImageIcon(icon8);
            case 9:
                return new ImageIcon(icon9);
            default:
                return new ImageIcon(icon0);
        }
    }
}
