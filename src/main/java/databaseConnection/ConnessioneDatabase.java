package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class ConnessioneDatabase
 */
public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    private Connection connection =  null;
    private String nome = "kalendario";
    private String password = "kalendario";
    private String url = "jdbc:postgresql://localhost:5432/kalendario";
    private String driver = "org.postgresql.Driver";


    /**
     * The class's constructor.
     *
     * @throws SQLException
     */
    private ConnessioneDatabase() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Impossibile creare la connessione");
            ex.printStackTrace();
        }
    }


    /**
     * Returns an instance of the class ConnessioneDataBase. If no instance is available, it creates a new one and returns it as output.
     *
     * @return The instance of ConnessioneDatabase
     * @throws SQLException
     */
    public static ConnessioneDatabase getInstance() throws SQLException {
        if(instance == null || instance.connection.isClosed()){
            instance = new ConnessioneDatabase();
        }

        return instance;
    }


    /**
     * Returns an  already open connection
     *
     * @return The connection
     */
    public Connection getConnection() {
        return connection;
    }

}
