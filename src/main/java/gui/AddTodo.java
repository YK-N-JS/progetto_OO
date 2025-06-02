package gui;

import controller.Controller;
import model.Bacheca;
import model.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class AddTodo extends JDialog {
    private JPanel contentPane;
    private Todo nuovo;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField titleTextField;
    private JTextField descriptionTextField;
    private JTextField urlTextield;
    private JTextField yearTextfield;
    private JComboBox monthComboBox;
    private JComboBox dayComboBox;
    public JFrame frame = new JFrame();

    public AddTodo(Controller controller, Bacheca bacheca, Todo nuovo_todo, JPanel bachecaPanel) {
        nuovo = nuovo_todo;
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        for(int i = 1; i<= 12; i++)
            monthComboBox.addItem(i);

        for(int j = 1; j<= 31; j++)
            dayComboBox.addItem(j);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int year = Integer.parseInt(yearTextfield.getText());
                int month = monthComboBox.getSelectedIndex()+1;
                int day = dayComboBox.getSelectedIndex()+1;
                if((day > 30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) || //giorno selezionato non è nel mese
                        (month == 2 && day > 28 && year%4 != 0) || //selezionato il 29 feb per anno non bisestile
                        (month == 2 && day > 29) //selezionato giorno maggiore del 29 per feb
                ){
                    JOptionPane.showMessageDialog(null, "Please select a valid date");
                } else {
                    nuovo.setTitle(titleTextField.getText());
                    nuovo.setDescription(descriptionTextField.getText());
                    nuovo.setUrl_activity(urlTextield.getText());
                    nuovo.setComplete_by_date(LocalDate.of(year, month, day));
                    bacheca.addTodo(nuovo);
                    JPanel todoPanel = new JPanel();
                    todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
                    JCheckBox todoCompletedBox = new JCheckBox(nuovo.getTitle());
                    bachecaPanel.add(todoPanel);
                    todoPanel.add(todoCompletedBox);
                    bachecaPanel.revalidate();
                    bachecaPanel.repaint();
                    //funziona, ma non sono sicuro perché
                    JButton removeTodoButton = new JButton("Remove");
                    todoPanel.add(removeTodoButton);
                    removeTodoButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                         bacheca.removeATodo(nuovo);
                         bachecaPanel.remove(todoPanel);
                         bachecaPanel.revalidate();
                         bachecaPanel.repaint();
                        }
                    });
                    JButton editTodoButton = new JButton("Edit");
                    todoPanel.add(editTodoButton);
                    editTodoButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EditTodoPage editTodoPage = new EditTodoPage(todoPanel,nuovo_todo);
                            editTodoPage.frame.setVisible(true);
                        }
                    });
                    frame.dispose();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bacheca.removeATodo(nuovo);
                nuovo = null;
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
}
