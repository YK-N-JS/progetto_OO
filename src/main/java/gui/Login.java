package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton createOneButton;
    private JPanel login;
    static JFrame frame = new JFrame("Login");
    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount createAccountwindow = new createAccount();
                createAccountwindow.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {

        frame.setContentPane(new Login().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
