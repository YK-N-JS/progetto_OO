package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private Connection connection =  null;
    private String nome = "kalendario";
    private String password = "kalendario";
    private String url = "jdbc:postgresql://localhost:5432/kalendario";
    private String driver = "org.postgresql.Driver";

    private ConnessioneDatabase() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossibile creare la connessione");
            ex.printStackTrace();
        }
    }

    public static ConnessioneDatabase getInstance() throws SQLException {
        if(instance == null){
            instance = new ConnessioneDatabase();
        } else if (instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
