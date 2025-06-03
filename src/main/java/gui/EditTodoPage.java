package gui;

import model.Icons;
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
    private JRadioButton cyanRadioButton;
    private JRadioButton blueRadioButton;
    private JTextField titleTextField;
    public  JFrame frame;
    private JPanel todoPanel;

    public EditTodoPage(JPanel todoPanel, Todo todo, JCheckBox todoCompletedbox, JLabel iconlabel) {
        Icons icons = new Icons();
        this.todoPanel = todoPanel;
        frame = new JFrame("Edit Todo");
        frame.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        frame.setMinimumSize(new Dimension(450, 200));

        descriptionTextField.setText(todo.getDescription());
        urlTextField.setText(todo.getUrl_activity());
        titleTextField.setText(todo.getTitle());

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(pinkRadioButton);
        colorGroup.add(yellowRadioButton);
        colorGroup.add(whiteRadioButton);
        colorGroup.add(cyanRadioButton);
        colorGroup.add(blueRadioButton);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                todo.setDescription(descriptionTextField.getText());
                todo.setUrl_activity(urlTextField.getText());
                todo.setTitle(titleTextField.getText());
                todoCompletedbox.setText(todo.getTitle());
                selectColor();

                iconlabel.setIcon(icons.getIcon(todo));

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
        } else if (cyanRadioButton.isSelected()){
            todoPanel.setBackground(Color.CYAN);
        } else if (blueRadioButton.isSelected()){
            todoPanel.setBackground(Color.BLUE);
        } else {
            //does nothing
        }
    }
}
