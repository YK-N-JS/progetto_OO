package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton createOneButton;
    protected static JFrame frame;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            CreateUser createUser = new CreateUser(frame);
            frame.setVisible(false);
            createUser.frame1.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Login");

        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 350));
        frame.pack();
        frame.setVisible(true);

    }
}
