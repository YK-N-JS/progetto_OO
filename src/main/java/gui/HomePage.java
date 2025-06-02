package gui;

import controller.Controller;
import model.Bacheca;
import model.Todo;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private User user;
    private Controller controller;
    public JFrame frame;
    private JLabel welcomeLable;
    private JPanel HomePage;
    private JButton addBachecaButton;
    private JButton deleteBachecaButton;
    private JTabbedPane tab_bacheche;

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
                tab_bacheche.add(bacheca.getTitle(), bachecaPanel);
                JLabel descrizione = new JLabel("Descrizione: " + bacheca.getDescription());
                bachecaPanel.add(descrizione);
                JButton editBachecaButton = new JButton("Edita Bacheca");
                JButton deleteBachecaButton = new JButton("Deleta Bacheca");
                JButton addTodoButton = new JButton("Add Todo");
                editBachecaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Edit_Bacheca tab = new Edit_Bacheca(controller, user.getBacheca(tab_bacheche.getSelectedIndex()), descrizione, tab_bacheche, bachecaPanel);
                        tab.frame.setVisible(true);
                    }

                });
                deleteBachecaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tab_bacheche.remove(tab_bacheche.getSelectedIndex());
                        user.removeBacheca(user.getBacheca(tab_bacheche.getSelectedIndex()));
                    }

                });
                addTodoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Todo nuovo = new Todo();
                        AddTodo todoPage = new AddTodo(controller, user.getBacheca(tab_bacheche.getSelectedIndex()), nuovo, bachecaPanel);
                        todoPage.frame.setVisible(true);
                    }

                });
                bachecaPanel.add(editBachecaButton);
                bachecaPanel.add(deleteBachecaButton);
                bachecaPanel.add(addTodoButton);
            }
        });


        frame = new JFrame("Kalen Dario di " + user.getUsername()); // è il nome della mia applicazione non prenderlo in giro
        frame.setContentPane(this.HomePage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 560));
        welcomeLable.setText("Welcome " + user.getUsername());
        for(Bacheca bacheca: user.getBacheche()){
            JPanel bachecaPanel = new JPanel();
            bachecaPanel.setLayout(new GridLayout(-1, 4));
            tab_bacheche.add(bacheca.getTitle(), bachecaPanel);
            JLabel descrizione = new JLabel(bacheca.getDescription());
            bachecaPanel.add(descrizione);
            JButton editBachecaButton = new JButton("Edit Bacheca");
            JButton deleteBachecaButton = new JButton("Delete Bacheca");
            JButton addTodoButton = new JButton("Add Todo");
            deleteBachecaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tab_bacheche.remove(tab_bacheche.getSelectedIndex());
                    user.removeBacheca(user.getBacheca(tab_bacheche.getSelectedIndex()));
                }

            });
            editBachecaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Edit_Bacheca tab = new Edit_Bacheca(controller, user.getBacheca(tab_bacheche.getSelectedIndex()), descrizione, tab_bacheche, bachecaPanel);
                    tab.frame.setVisible(true);
                }

            });
            addTodoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Todo nuovo = new Todo();
                    AddTodo todoPage = new AddTodo(controller, user.getBacheca(tab_bacheche.getSelectedIndex()), nuovo, bachecaPanel);
                    todoPage.frame.setVisible(true);
                }

            });
            bachecaPanel.add(editBachecaButton);
            bachecaPanel.add(deleteBachecaButton);
            bachecaPanel.add(addTodoButton);
            for(Todo todo : user.getBacheca(tab_bacheche.getSelectedIndex()).getTodoInBacheca()) {
                JPanel todoPanel = new JPanel();
                todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
                JCheckBox todoCompletedBox = new JCheckBox(todo.getTitle());
                bachecaPanel.add(todoPanel);
                todoPanel.add(todoCompletedBox);
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
                        EditTodoPage editTodoPage = new EditTodoPage(todoPanel, todo);
                        editTodoPage.frame.setVisible(true);
                    }
                });
            }
        }
    }
    //TODO sharing todos between bachecas and users
    //TODO change position of todos in bacheca using DB
    //TODO checks if todo is expired and expires it
    //TODO sets todos to complete if box is checked
    //TODO figure out a way to add pics
    //TODO make todo COMPLETELY customizable
}
