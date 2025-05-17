package gui;

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
    public JFrame frame1;

    public CreateUser(JFrame frame) {
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
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
