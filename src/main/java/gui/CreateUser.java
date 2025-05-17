package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateUser extends JDialog {
    //frame IS PARENT
    //frame1 IS CHILD
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton createButton;
    public JFrame frame1;

    public CreateUser(JFrame frame, Controller controller) {
        frame1 = new JFrame("Create User");
        frame1.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        frame1.setMinimumSize(new Dimension(400, 300));

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                frame1.dispose();
            }
        });

        // call onCancel() when cross is clicked
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //TODO create and add user to userlist
                if (!passwordField1.getText().equals(passwordField2.getText())) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                } else if (passwordField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password must be entered");
                }else if (controller.checkNewUser(textField1.getText())) {
                    controller.addUser(textField1.getText(), passwordField1.getText());
                    frame.setVisible(true);
                    frame1.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists");
                }
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
