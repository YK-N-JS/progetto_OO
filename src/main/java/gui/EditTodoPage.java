package gui;

import model.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

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
    private JTextField yearTextfield;
    private JComboBox monthComboBox;
    private JComboBox dayComboBox;
    public  JFrame frame;
    private JPanel todoPanel;


    public EditTodoPage(JPanel todoPanel, Todo todo, JCheckBox todoCompletedBox) {
        this.todoPanel = todoPanel;
        frame = new JFrame("Edit Todo");
        frame.setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        frame.setMinimumSize(new Dimension(900, 600));

        for(int i = 1; i<= 12; i++)
            monthComboBox.addItem(i);
        for(int j = 1; j<= 31; j++)
            dayComboBox.addItem(j);


        descriptionTextField.setText(todo.getDescription());
        urlTextField.setText(todo.getUrl_activity());
        titleTextField.setText(todo.getTitle());
        yearTextfield.setText(String.valueOf(todo.getComplete_by_date().getYear()));
        monthComboBox.setSelectedItem(todo.getComplete_by_date().getMonthValue());
        dayComboBox.setSelectedItem(todo.getComplete_by_date().getDayOfMonth());


        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(pinkRadioButton);
        colorGroup.add(yellowRadioButton);
        colorGroup.add(whiteRadioButton);
        colorGroup.add(cyanRadioButton);
        colorGroup.add(blueRadioButton);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int year = Integer.parseInt(yearTextfield.getText());
                int month = monthComboBox.getSelectedIndex()+1;
                int day = dayComboBox.getSelectedIndex()+1;

                if((day > 30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) ||
                        (month == 2 && day > 28 && year%4 != 0) ||
                        (month == 2 && day > 29))
                    JOptionPane.showMessageDialog(null, "Please select a valid date");
                else {
                    todo.setDescription(descriptionTextField.getText());
                    todo.setUrl_activity(urlTextField.getText());
                    todo.setTitle(titleTextField.getText());
                    todoCompletedBox.setText(todo.getTitle());
                    selectColor();

                    todo.setComplete_by_date(LocalDate.of(year, month, day));

                    todoCompletedBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(todoCompletedBox.isSelected()){
                                todo.setStatus("completed");
                                todoPanel.setBackground(Color.GREEN);
                            } else if (!todoCompletedBox.isSelected()) {
                                todo.setStatus("to complete");
                                todoPanel.setBackground(Color.WHITE);
                                if(LocalDate.now().isAfter(todo.getComplete_by_date()))
                                {
                                    todoPanel.setBackground(Color.RED);
                                }
                            }
                        }
                    });
                    frame.dispose();
                }
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
        }
    }
}
