package gui;

import controller.Controller;
import model.Bacheca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Edit_Bacheca extends JDialog {
    public JFrame frame;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel nome;
    private JLabel descrizione;
    private JTextField nome_textfield;
    private JTextField descrizione_textfield;
    private JRadioButton pinkRadioButton;
    private JRadioButton redRadioButton;
    private JRadioButton whiteRadioButton;
    private JRadioButton yellowRadioButton;
    private JRadioButton cyanRadioButton;
    private JRadioButton blueRadioButton;
    private ButtonGroup buttonGroup;

    public Edit_Bacheca(Controller controller, Bacheca bacheca, JLabel descrizione_label, JTabbedPane tabbedPane, JPanel panel) {
        frame = new JFrame("Edit Bacheca");
        frame.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        frame.setMinimumSize(new Dimension(450, 200));

        nome_textfield.setText(bacheca.getTitle());
        descrizione_textfield.setText(bacheca.getDescription());

        buttonGroup = new ButtonGroup();
        buttonGroup.add(pinkRadioButton);
        buttonGroup.add(redRadioButton);
        buttonGroup.add(whiteRadioButton);
        buttonGroup.add(yellowRadioButton);
        buttonGroup.add(cyanRadioButton);
        buttonGroup.add(blueRadioButton);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bacheca.setTitle(nome_textfield.getText());
                bacheca.setDescription(descrizione_textfield.getText());
                descrizione_label.setText(bacheca.getDescription());
                tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), bacheca.getTitle());
                setColor(panel);
                frame.dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    public void setColor(JPanel panel)
    {
        if(pinkRadioButton.isSelected()){
            panel.setBackground(Color.pink);
        } else if (redRadioButton.isSelected()){
            panel.setBackground(Color.red);
        } else if (whiteRadioButton.isSelected()){
            panel.setBackground(Color.white);
        } else if (yellowRadioButton.isSelected()){
            panel.setBackground(Color.yellow);
        } else if (cyanRadioButton.isSelected()){
            panel.setBackground(Color.cyan);
        } else if (blueRadioButton.isSelected()){
            panel.setBackground(Color.blue);
        } else {
            panel.setBackground(Color.white);
        }
    }
}
