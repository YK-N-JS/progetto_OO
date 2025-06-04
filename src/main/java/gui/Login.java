package gui;

import controller.Controller;
import model.Bacheca;
import model.Todo;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Login {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton createOneButton;
    protected static JFrame frame;
    private static Controller controller = new Controller();

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.login(textField1.getText(), passwordField1.getText()) == 0) {
                    //MAGARI FA QUALCOSA DI PIÙ INTERESSANTE...
                    //JOptionPane.showMessageDialog(frame, "Login Successful");
                    HomePage homePage = new HomePage(controller.getUser(textField1.getText()), controller);
                    homePage.frame.setVisible(true);
                    frame.dispose();

                } else if (controller.login(textField1.getText(), passwordField1.getText()) == -1) {
                    JOptionPane.showMessageDialog(frame, "wrong password");
                } else if (controller.login(textField1.getText(), passwordField1.getText()) == -2) {
                    JOptionPane.showMessageDialog(frame, "invalid user");
                }
            }
        });

        createOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            CreateUser createUser = new CreateUser(frame, controller);
            frame.setVisible(false);
            createUser.childFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        //handles the look and feel of the application, set it to GTK+, looks better
       /* try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }*/

        //FAKE USER FOR DEBUGGING PURPOSES
        controller.addUser("0", "0");
        Todo zero = new Todo();
        User a = controller.getUser("0");
        Bacheca b = a.getBacheca(0);
        b.addTodo(zero);
        zero.setComplete_by_date(LocalDate.now());
        zero.setTitle("ciao");
        //

        frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 350));
        frame.pack();
        frame.setVisible(true);
    }
}
