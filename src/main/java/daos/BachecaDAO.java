package daos;

import controller.Controller;
import databaseConnection.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class BachecaDAO {
    private Connection connection;
    private Controller controller;
    public BachecaDAO(Controller controller) {
        this.controller = controller;
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
