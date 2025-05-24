package gui;

import controller.Controller;
import model.Bacheca;
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
    private JButton editBachecaButton = new JButton("Edit Bacheca");
    //private JButton deleteBachecaButton;
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
                tab_bacheche.add(bacheca.getTitle(), bachecaPanel);
                JLabel descrizione = new JLabel("Descrizione: " + bacheca.getDescription());
                bachecaPanel.add(descrizione);
            }
        });
        editBachecaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edit_Bacheca tab = new Edit_Bacheca(controller, user.getBacheca(tab_bacheche.getSelectedIndex()));
                tab.contentPane.setVisible(true);
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
            tab_bacheche.add(bacheca.getTitle(), bachecaPanel);
            JLabel descrizione = new JLabel(bacheca.getDescription());
            bachecaPanel.add(descrizione);
            bachecaPanel.add(editBachecaButton);
        }
        //TODO va finito, da ggiungere le viste per le bahceche, la documentazione, e i todo


    }
}
