package gui;

import controller.Controller;
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
    private JRadioButton radioButton0;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton radioButton8;
    private JRadioButton radioButton9;
    public  JFrame frame;
    private JPanel todoPanel;
    private Todo todo;


    public EditTodoPage(JPanel todoPanel, Todo todo, JCheckBox todoCompletedBox, Controller controller, JLabel todoImage) {
        this.todoPanel = todoPanel;
        this.todo = todo;
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

        radioButton0.setIcon(controller.getIconByNumber(0));
        radioButton1.setIcon(controller.getIconByNumber(1));
        radioButton2.setIcon(controller.getIconByNumber(2));
        radioButton3.setIcon(controller.getIconByNumber(3));
        radioButton4.setIcon(controller.getIconByNumber(4));
        radioButton5.setIcon(controller.getIconByNumber(5));
        radioButton6.setIcon(controller.getIconByNumber(6));
        radioButton7.setIcon(controller.getIconByNumber(7));
        radioButton8.setIcon(controller.getIconByNumber(8));
        radioButton9.setIcon(controller.getIconByNumber(9));



        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int year = Integer.parseInt(yearTextfield.getText());
                int month = monthComboBox.getSelectedIndex()+1;
                int day = dayComboBox.getSelectedIndex()+1;

                selectIcon();
                todoImage.setIcon(controller.getIcon(todo));

                if((day > 30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) ||
                        (month == 2 && day > 28 && year%4 != 0) ||
                        (month == 2 && day > 29))
                    JOptionPane.showMessageDialog(null, "Please select a valid date");
                else {
                    todo.setDescription(descriptionTextField.getText());
                    todo.setUrl_activity(urlTextField.getText());
                    todo.setTitle(titleTextField.getText());
                    todoCompletedBox.setText(todo.getTitle());
                    todo.setColor(selectColor());

                    todo.setComplete_by_date(LocalDate.of(year, month, day));

                    todoCompletedBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(todoCompletedBox.isSelected()){
                                todo.setStatus(true);
                                todoPanel.setBackground(Color.GREEN);
                            } else if (!todoCompletedBox.isSelected()) {
                                todo.setStatus(false);
                                todoPanel.setBackground(Color.WHITE);
                                if(LocalDate.now().isAfter(todo.getComplete_by_date()))
                                {
                                    todoPanel.setBackground(Color.RED);
                                }
                            }
                        }
                    });
                    controller.editTodo(todo);
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


    public int selectColor()
    {
        if (pinkRadioButton.isSelected()){
            todoPanel.setBackground(Color.PINK);
            return 1;
        } else if (yellowRadioButton.isSelected()){
            todoPanel.setBackground(Color.YELLOW);
            return 2;
        } else if (whiteRadioButton.isSelected()){
            todoPanel.setBackground(Color.WHITE);
            return 0;
        } else if (cyanRadioButton.isSelected()){
            todoPanel.setBackground(Color.CYAN);
            return 4;
        } else if (blueRadioButton.isSelected()){
            todoPanel.setBackground(Color.BLUE);
            return 3;
        }
        return 0;
    }

    public void selectIcon()
    {
        if(radioButton0.isSelected()){
            todo.setIcon(0);
        } else if(radioButton1.isSelected()){
            todo.setIcon(1);
        } else if(radioButton2.isSelected()){
            todo.setIcon(2);
        } else if(radioButton3.isSelected()){
            todo.setIcon(3);
        } else if(radioButton4.isSelected()){
            todo.setIcon(4);
        } else if(radioButton5.isSelected()){
            todo.setIcon(5);
        } else if(radioButton6.isSelected()){
            todo.setIcon(6);
        } else if(radioButton7.isSelected()){
            todo.setIcon(7);
        } else if(radioButton8.isSelected()){
            todo.setIcon(8);
        }  else if(radioButton9.isSelected()){
            todo.setIcon(9);
        }
    }
}
