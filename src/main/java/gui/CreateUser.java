package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateUser extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton createButton;
    public JFrame childFrame;


    public CreateUser(JFrame parentFrame, Controller controller) {
        childFrame = new JFrame("Create User");
        childFrame.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        childFrame.setMinimumSize(new Dimension(400, 300));

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                childFrame.dispose();
            }
        });

        // call onCancel() when cross is clicked
        childFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
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
                if (!passwordField1.getText().equals(passwordField2.getText())) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                } else if (passwordField1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password must be entered");
                }else if (controller.checkNewUser(textField1.getText())) {
                    controller.addUser(textField1.getText(), passwordField1.getText());
                    parentFrame.setVisible(true);
                    childFrame.dispose();
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
