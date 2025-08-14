package gui;

import controller.Controller;
import model.Bacheca;
import model.Todo;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

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

    public AddTodo(Bacheca bacheca, Todo nuovoTodo, JPanel bachecaPanel, Controller controller, String username) {
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        nuovo = nuovoTodo;


        for(int i = 1; i<= 12; i++)
            monthComboBox.addItem(i);
        for(int j = 1; j<= 31; j++)
            dayComboBox.addItem(j);

        monthComboBox.setSelectedItem(0);
        dayComboBox.setSelectedItem(0);


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
                    controller.addTodo(bacheca, nuovo, username);

                    JPanel todoPanel = new JPanel();
                    todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
                    JCheckBox todoCompletedBox = new JCheckBox(nuovo.getTitle());
                    bachecaPanel.add(todoPanel);
                    todoPanel.add(todoCompletedBox);

                    if(LocalDate.now().isAfter(nuovo.getComplete_by_date()))
                    {
                        todoPanel.setBackground(Color.red);
                    }
                    todoCompletedBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(todoCompletedBox.isSelected()){
                                nuovo.setStatus(true);
                                todoPanel.setBackground(Color.GREEN);
                            } else if (!todoCompletedBox.isSelected()) {
                                nuovo.setStatus(false);
                                todoPanel.setBackground(Color.WHITE);
                                if(LocalDate.now().isAfter(nuovo.getComplete_by_date()))
                                {
                                    todoPanel.setBackground(Color.red);
                                }
                            }
                        }
                    });

                    bachecaPanel.revalidate();
                    bachecaPanel.repaint();

                    JButton removeTodoButton = new JButton("Remove");
                    todoPanel.add(removeTodoButton);

                    removeTodoButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                         controller.removeTodo(nuovo, bacheca);
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
                            EditTodoPage editTodoPage = new EditTodoPage(todoPanel,nuovoTodo, todoCompletedBox, controller);
                            editTodoPage.frame.setVisible(true);
                        }
                    });

                    JButton condividiTodoButton = new JButton("Condividi");
                    todoPanel.add(condividiTodoButton);
                    condividiTodoButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String  bersaglioCondivisione;
                            bersaglioCondivisione = JOptionPane.showInputDialog("Inserirsci nome utente: ");
                            controller.shareTodo(nuovo.getID(), bersaglioCondivisione);
                        }
                    });
                    frame.dispose();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.removeTodo(nuovo, bacheca);
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
