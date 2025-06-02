package gui;

import model.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditTodoPage extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descriptionTextField;
    private JTextField urlTextField;
    private JRadioButton pinkRadioButton;
    private JRadioButton yellowRadioButton;
    private JRadioButton whiteRadioButton;
    private JRadioButton greenRadioButton;
    private JRadioButton blueRadioButton;
    public  JFrame frame;
    private JPanel todoPanel;

    public EditTodoPage(JPanel todoPanel, Todo todo) {
        this.todoPanel = todoPanel;
        frame = new JFrame("Edit Todo");
        frame.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        frame.setMinimumSize(new Dimension(450, 200));

        descriptionTextField.setText(todo.getDescription());
        urlTextField.setText(todo.getUrl_activity());

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(pinkRadioButton);
        colorGroup.add(yellowRadioButton);
        colorGroup.add(whiteRadioButton);
        colorGroup.add(greenRadioButton);
        colorGroup.add(blueRadioButton);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                todo.setDescription(descriptionTextField.getText());
                todo.setUrl_activity(urlTextField.getText());
                selectColor();
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

    public void selectColor()
    {
        if (pinkRadioButton.isSelected()){
            todoPanel.setBackground(Color.PINK);
        } else if (yellowRadioButton.isSelected()){
            todoPanel.setBackground(Color.YELLOW);
        } else if (whiteRadioButton.isSelected()){
            todoPanel.setBackground(Color.WHITE);
        } else if (greenRadioButton.isSelected()){
            todoPanel.setBackground(Color.GREEN);
        } else if (blueRadioButton.isSelected()){
            todoPanel.setBackground(Color.BLUE);
        } else {
            //does nothing
        }
    }
}
