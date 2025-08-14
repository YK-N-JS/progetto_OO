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
                bacheca.setTitle(JOptionPane.showInputDialog("Enter title: "));
                controller.addBacheca(user.getUsername(), user.getPassword(), bacheca);

                JPanel bachecaPanel = new JPanel();
                bachecaPanel.setLayout(new GridLayout(-1, 6));
                tabBacheche.add(bacheca.getTitle(), bachecaPanel);

                JLabel descrizione = new JLabel("Descrizione: " + bacheca.getDescription());
                bachecaPanel.add(descrizione);

                JButton editBachecaButton = new JButton("Edita Bacheca");
                JButton deleteBachecaButton = new JButton("Deleta Bacheca");
                JButton sortTodoAlphabeticalButton = new JButton("Sort ALphabetical");
                JButton sortTodoByDateButton = new JButton("Sort by date");
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
                        if(controller.deleteBacheca(bacheca, user)){
                            tabBacheche.remove(tabBacheche.getSelectedIndex());
                        } else {
                            JOptionPane.showMessageDialog(null, "Impossibile eliminare la bacheca di default");
                        }
                    }
                });

                sortTodoAlphabeticalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.getTodoAlphabetical(bacheca);
                        bachecaPanel.revalidate();
                        bachecaPanel.repaint();
                    }
                });

                sortTodoByDateButton.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       controller.getTodoByDate(bacheca);
                       bachecaPanel.revalidate();
                       bachecaPanel.repaint();
                   }
                });

                addTodoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Todo nuovo = new Todo();
                        AddTodo todoPage = new AddTodo(user.getBacheca(tabBacheche.getSelectedIndex()), nuovo, bachecaPanel, controller, user.getUsername());
                        todoPage.frame.setVisible(true);
                    }
                });

                bachecaPanel.add(editBachecaButton);
                bachecaPanel.add(deleteBachecaButton);
                bachecaPanel.add(sortTodoAlphabeticalButton);
                bachecaPanel.add(sortTodoByDateButton);
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

        controller.loadBachecheUser(user);

        for(Bacheca bacheca: user.getBacheche()){
            JPanel bachecaPanel = new JPanel();
            bachecaPanel.setLayout(new GridLayout(-1, 6));
            tabBacheche.add(bacheca.getTitle(), bachecaPanel);

            addEverythingToBacheca(bachecaPanel, bacheca);

            for(Todo todo : controller.getTodoByDate(bacheca)) {
                JPanel todoPanel = new JPanel();
                todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
                JCheckBox todoCompletedBox = new JCheckBox(todo.getTitle());

                bachecaPanel.add(todoPanel);
                todoPanel.add(todoCompletedBox);
                
               if(LocalDate.now().isAfter(todo.getComplete_by_date()) && !todo.getStatus()){
                    todoPanel.setBackground(Color.red);
                } else if (todo.getStatus()) {
                    todoCompletedBox.setSelected(true);
                }

                todoCompletedBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(todoCompletedBox.isSelected()){
                            todo.setStatus(true);
                            todoPanel.setBackground(Color.green);
                        } else if (!todoCompletedBox.isSelected()) {
                            todo.setStatus(false);
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
                        controller.removeTodo(todo, bacheca);
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
                        EditTodoPage editTodoPage = new EditTodoPage(todoPanel, todo, todoCompletedBox, controller);
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


    public void reloadTodoByDate(Bacheca bacheca, JPanel bachecaPanel) {
        for(Todo todo : controller.getTodoByDate(bacheca)) {
            JPanel todoPanel = new JPanel();
            todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
            JCheckBox todoCompletedBox = new JCheckBox(todo.getTitle());

            bachecaPanel.add(todoPanel);
            todoPanel.add(todoCompletedBox);
            todoPanel.setBackground(checkColor(todo));

            if (todo.getStatus()) {
                todoCompletedBox.setSelected(true);
            }

            todoCompletedBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(todoCompletedBox.isSelected()){
                        todo.setStatus(true);
                        todoPanel.setBackground(Color.green);
                        todo.setColor(5);
                    } else if (!todoCompletedBox.isSelected()) {
                        todo.setStatus(false);
                        todoPanel.setBackground(Color.white);
                        if(LocalDate.now().isAfter(todo.getComplete_by_date())){
                            todoPanel.setBackground(Color.red);
                            todo.setColor(6);
                        }
                    }
                    controller.editTodo(todo);
                }
            });

            JButton removeTodoButton = new JButton("Remove");
            todoPanel.add(removeTodoButton);
            removeTodoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.removeTodo(todo, bacheca);
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
                    EditTodoPage editTodoPage = new EditTodoPage(todoPanel, todo, todoCompletedBox, controller);
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



    public void reloadTodoAlphabetical(Bacheca bacheca, JPanel bachecaPanel) {
        for(Todo todo : controller.getTodoAlphabetical(bacheca)) {
            JPanel todoPanel = new JPanel();
            todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.Y_AXIS));
            JCheckBox todoCompletedBox = new JCheckBox(todo.getTitle());

            bachecaPanel.add(todoPanel);
            todoPanel.add(todoCompletedBox);
            todoPanel.setBackground(checkColor(todo));

            if (todo.getStatus()) {
                todoCompletedBox.setSelected(true);
            }

            todoCompletedBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(todoCompletedBox.isSelected()){
                        todo.setStatus(true);
                        todoPanel.setBackground(Color.green);
                        todo.setColor(5);
                    } else if (!todoCompletedBox.isSelected()) {
                        todo.setStatus(false);
                        todoPanel.setBackground(Color.white);
                        if(LocalDate.now().isAfter(todo.getComplete_by_date())){
                            todoPanel.setBackground(Color.red);
                            todo.setColor(6);
                        }
                    }
                    controller.editTodo(todo);
                }
            });

            JButton removeTodoButton = new JButton("Remove");
            todoPanel.add(removeTodoButton);
            removeTodoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.removeTodo(todo, bacheca);
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
                    EditTodoPage editTodoPage = new EditTodoPage(todoPanel, todo, todoCompletedBox, controller);
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

    public void addEverythingToBacheca(JPanel bachecaPanel, Bacheca  bacheca) {
        JLabel descrizione = new JLabel(bacheca.getDescription());
        bachecaPanel.add(descrizione);
        JButton editBachecaButton = new JButton("Edit Bacheca");
        JButton deleteBachecaButton = new JButton("Delete Bacheca");
        JButton sortTodoAlphabeticalButton = new JButton("Sort alphabetical");
        JButton sortTodoByDateButton = new JButton("Sort by date");
        JButton addTodoButton = new JButton("Add Todo");

        deleteBachecaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.deleteBacheca(bacheca, user)){
                    tabBacheche.remove(tabBacheche.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(null, "Impossibile eliminare la bacheca di default");
                }
            }
        });

        editBachecaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit_Bacheca tab = new Edit_Bacheca(controller, user.getBacheca(tabBacheche.getSelectedIndex()), descrizione, tabBacheche, bachecaPanel);
                tab.frame.setVisible(true);
            }
        });

        sortTodoAlphabeticalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bachecaPanel.removeAll();
                addEverythingToBacheca(bachecaPanel,bacheca);
                reloadTodoAlphabetical(bacheca, bachecaPanel);
                bachecaPanel.revalidate();
                bachecaPanel.repaint();
            }
        });

        sortTodoByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bachecaPanel.removeAll();
                addEverythingToBacheca(bachecaPanel, bacheca);
                reloadTodoByDate(bacheca, bachecaPanel);
                bachecaPanel.revalidate();
                bachecaPanel.repaint();
            }
        });

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Todo nuovo = new Todo();
                AddTodo todoPage = new AddTodo(user.getBacheca(tabBacheche.getSelectedIndex()), nuovo, bachecaPanel, controller, user.getUsername());
                todoPage.frame.setVisible(true);
            }
        });

        bachecaPanel.add(editBachecaButton);
        bachecaPanel.add(deleteBachecaButton);
        bachecaPanel.add(sortTodoAlphabeticalButton);
        bachecaPanel.add(sortTodoByDateButton);
        bachecaPanel.add(addTodoButton);
    }

    public Color checkColor(Todo todo)
    {
        int color = todo.getColor();
        switch (color)
        {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.PINK;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.CYAN;
            case 5:
                return Color.GREEN;
            case 6:
                return Color.RED;
        }

        return Color.WHITE;
    }
    //TODO status todo da salvare/modificare
    //TODO figure out a way to add pics
}
