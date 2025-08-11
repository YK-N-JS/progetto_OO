package gui;

import controller.Controller;
import model.Bacheca;
import model.Todo;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class HomePage {
    private User user;
    private Controller controller;
    public JFrame frame;
    private JLabel welcomeLable;
    private JPanel homePagePanel;
    private JButton addBachecaButton;
    private JTabbedPane tabBacheche;


    public HomePage(User user, Controller controller) {
        this.user = user;
        this.controller = controller;
        addBachecaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bacheca bacheca = new Bacheca();
                user.addBacheca(bacheca);
                bacheca.setTitle(JOptionPane.showInputDialog("Enter title: "));
                JPanel bachecaPanel = new JPanel();
                bachecaPanel.setLayout(new GridLayout(-1, 4));
                tabBacheche.add(bacheca.getTitle(), bachecaPanel);

                JLabel descrizione = new JLabel("Descrizione: " + bacheca.getDescription());
                bachecaPanel.add(descrizione);

                JButton editBachecaButton = new JButton("Edita Bacheca");
                JButton deleteBachecaButton = new JButton("Deleta Bacheca");
                JButton addTodoButton = new JButton("Add Todo");

                editBachecaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Edit_Bacheca tab = new Edit_Bacheca(controller, user.getBacheca(tabBacheche.getSelectedIndex()), descrizione, tabBacheche, bachecaPanel);
                        tab.frame.setVisible(true);
                    }
                });

                deleteBachecaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tabBacheche.remove(tabBacheche.getSelectedIndex());
                        user.removeBacheca(user.getBacheca(tabBacheche.getSelectedIndex()));
                    }
                });

                addTodoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Todo nuovo = new Todo();
                        AddTodo todoPage = new AddTodo(user.getBacheca(tabBacheche.getSelectedIndex()), nuovo, bachecaPanel, controller);
                        todoPage.frame.setVisible(true);
                    }
                });

                bachecaPanel.add(editBachecaButton);
                bachecaPanel.add(deleteBachecaButton);
                bachecaPanel.add(addTodoButton);
            }
        });


        frame = new JFrame("Kalen Dario di " + user.getUsername()); // è il nome della mia applicazione non prenderlo in giro
        frame.setContentPane(this.homePagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(1500, 599));
        welcomeLable.setText("Welcome " + user.getUsername());

        for(Bacheca bacheca: user.getBacheche()){
            JPanel bachecaPanel = new JPanel();
            bachecaPanel.setLayout(new GridLayout(-1, 4));
            tabBacheche.add(bacheca.getTitle(), bachecaPanel);
            JLabel descrizione = new JLabel(bacheca.getDescription());
            bachecaPanel.add(descrizione);
            JButton editBachecaButton = new JButton("Edit Bacheca");
            JButton deleteBachecaButton = new JButton("Delete Bacheca");
            JButton addTodoButton = new JButton("Add Todo");

            deleteBachecaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tabBacheche.remove(tabBacheche.getSelectedIndex());
                    user.removeBacheca(user.getBacheca(tabBacheche.getSelectedIndex()));
                }
            });

            editBachecaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Edit_Bacheca tab = new Edit_Bacheca(controller, user.getBacheca(tabBacheche.getSelectedIndex()), descrizione, tabBacheche, bachecaPanel);
                    tab.frame.setVisible(true);
                }
            });

            addTodoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Todo nuovo = new Todo();
                    AddTodo todoPage = new AddTodo(user.getBacheca(tabBacheche.getSelectedIndex()), nuovo, bachecaPanel, controller);
                    todoPage.frame.setVisible(true);
                }
            });

            bachecaPanel.add(editBachecaButton);
            bachecaPanel.add(deleteBachecaButton);
            bachecaPanel.add(addTodoButton);

            for(Todo todo : bacheca.getTodoInBacheca()) {
                JPanel todoPanel = new JPanel();
                todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
                JCheckBox todoCompletedBox = new JCheckBox(todo.getTitle());

                bachecaPanel.add(todoPanel);
                todoPanel.add(todoCompletedBox);
                
               if(LocalDate.now().isAfter(todo.getComplete_by_date()) && !todo.getStatus().equals("completed")){
                    todoPanel.setBackground(Color.red);
                } else if (todo.getStatus().equals("completed")) {
                    todoCompletedBox.setSelected(true);
                }

                todoCompletedBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(todoCompletedBox.isSelected()){
                            todo.setStatus("completed");
                            todoPanel.setBackground(Color.green);
                        } else if (!todoCompletedBox.isSelected()) {
                            todo.setStatus("to complete");
                            todoPanel.setBackground(Color.white);
                            if(LocalDate.now().isAfter(todo.getComplete_by_date())){
                                todoPanel.setBackground(Color.red);
                            }
                        }
                    }
                });

                JButton removeTodoButton = new JButton("Remove");
                todoPanel.add(removeTodoButton);
                removeTodoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bacheca.removeATodo(todo);
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
                        EditTodoPage editTodoPage = new EditTodoPage(todoPanel, todo, todoCompletedBox);
                        editTodoPage.frame.setVisible(true);
                    }
                });

                JButton condividiTodoButton = new JButton("Condividi");
                todoPanel.add(condividiTodoButton);

                condividiTodoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String bersaglioCondivisione;
                        bersaglioCondivisione = JOptionPane.showInputDialog("Inserirsci nome utente: ");
                        controller.shareTodo(todo.getID(), bersaglioCondivisione);
                    }
                });
            }
        }
    }

    //TODO function for refreshing main page
    //TODO figure out a way to add pics
}
