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
    private BufferedImage icon0 = null;
    private BufferedImage icon1 =  null;
    private BufferedImage icon2 =  null;
    private BufferedImage icon3 =   null;
    private BufferedImage icon4 =   null;
    private BufferedImage icon5 =   null;
    private BufferedImage icon6  =   null;
    private BufferedImage icon7 =   null;
    private BufferedImage icon8  =   null;
    private BufferedImage icon9 =   null;


    /**
     * The constructor for the Icon class
     * Throws a generic Exception if it fails to locate the image files
     *
     * @throws Exception
     */
    public Icon() {
        try {
            icon0 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/edit.png"));
            icon1 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/home.png"));
            icon2 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/like.png"));
            icon3 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/map.png"));
            icon4 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/music-player.png"));
            icon5 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/muted.png"));
            icon6 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/smartphone.png"));
            icon7 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/stopwatch.png"));
            icon8 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/user.png"));
            icon9 = ImageIO.read(new File("/home/davide/Programs/java/progetto_OO/src/main/resources/images/users.png"));
        }
        catch(IOException e) {
        System.out.println("No images found");
        e.printStackTrace();
        }
    }

    /**
     * Takes a T0do as input, gets its Icon value and returns its corresponding image as an ImageIcon
     *
     * @param todo
     * @return ImageIcon
     */
    public ImageIcon getIcon(Todo todo){
        switch(todo.getIconID()) {
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

    public ImageIcon getIconBYNumber(int iconId)
    {
        switch(iconId){
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
