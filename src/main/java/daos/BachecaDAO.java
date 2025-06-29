package daos;

import controller.Controller;
import databaseConnection.ConnessioneDatabase;
import model.Bacheca;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Bacheca> getAllBacheca(User user) {
        try{
            ArrayList<Bacheca> bacheche = new ArrayList<Bacheca>();
            PreparedStatement recuperaBacheche = connection.prepareStatement("Select * From Bacheca where \"Owner\" = ?");
            recuperaBacheche.setString(1, user.getUsername());
            ResultSet resultSet = recuperaBacheche.executeQuery();
            while (resultSet.next()) {
                bacheche.add(new Bacheca(resultSet.getInt("\"ID\""), resultSet.getString("Title"),
                controller.getUser(resultSet.getString("\"Owner\"")), resultSet.getString("Description"), resultSet.getBoolean("IsDefault")));
            }
            connection.close();
            return bacheche;

        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void insertBacheca(Bacheca bacheca) {
        try{
            PreparedStatement inserisciBacheca = connection.prepareStatement("Insert into Bacheca(Title, Description, \"Owner\") values(?,?,?)");
            inserisciBacheca.setString(1, bacheca.getTitle());
            inserisciBacheca.setString(2, bacheca.getDescription());
            inserisciBacheca.setString(3, bacheca.getOwner().getUsername());

            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBacheca(Bacheca bacheca) {
        try{
            PreparedStatement rimuoviBacheca = connection.prepareStatement("Delete from Bacheca where \"ID\" = ?");
            rimuoviBacheca.setInt(1, bacheca.getId());

            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}
