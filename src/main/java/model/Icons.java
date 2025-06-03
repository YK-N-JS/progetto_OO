package model;

import javax.swing.*;

public class Icons {
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    private ImageIcon icon5;
    private ImageIcon icon6;
    private ImageIcon icon7;
    private ImageIcon icon8;
    private ImageIcon icon9;
    private ImageIcon icon10;


    public Icons() {
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("../../icons/edit.png"));
        this.icon1 = icon1;
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("../../icons/home.png"));
        this.icon2 = icon2;
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("../../icons/like.png"));
        this.icon3 = icon3;
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("../../icons/map.png"));
        this.icon4 = icon4;
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("../../icons/music-player.png"));
        this.icon5 = icon5;
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("../../icons/muted.png"));
        this.icon6 = icon6;
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("../../icons/smartphone.png"));
        this.icon7 = icon7;
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("../../icons/stopwatch.png"));
        this.icon8 = icon8;
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("../../icons/user.png"));
        this.icon9 = icon9;
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("../../icons/users.png"));
        this.icon10 = icon10;
    }

    public ImageIcon getIcon(Todo todo){
        switch(todo.getIcon()) {
            case 0:
                return null;
            case 1:
                return icon1;
            case 2:
                return icon2;
            case 3:
                return icon3;
            case 4:
                return icon4;
            case 5:
                return icon5;
            case 6:
                return icon6;
            case 7:
                return icon7;
            case 8:
                return icon8;
            case 9:
                return icon9;
            case 10:
                return icon10;
        }
        return null;
    }

}
