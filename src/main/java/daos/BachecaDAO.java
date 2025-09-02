package daos;

import databaseConnection.ConnessioneDatabase;
import model.Bacheca;

import java.sql.*;
import java.util.ArrayList;

/**
 * The class BachecaDAO
 */
public class BachecaDAO {
    private Connection connection;


    /**
     * The constructor for BachecaDAO
     */
    public BachecaDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Takes as input a username and returns all the bacheca's of the given user.
     *
     * @param username The username of the user
     * @return an array list of this user's bacheche
     */
    public ArrayList<Bacheca> getAllBacheca(String username) {
        try{
            ArrayList<Bacheca> bacheche = new ArrayList<>();
            PreparedStatement recuperaBacheche = connection.prepareStatement("Select * From Bacheca where \"Owner\" = ?");
            recuperaBacheche.setString(1, username);
            ResultSet resultSet = recuperaBacheche.executeQuery();
            while (resultSet.next()) {
                bacheche.add(new Bacheca(resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getBoolean("IsDefault")));
            }
            return bacheche;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Takes as input a new bacheca and its owner's username and adds it to  the database.
     *
     * @param bacheca The bacheca to be added
     * @param username The bacheca's owner's username
     */
    public void createBacheca(Bacheca bacheca, String username) {
        try{
            PreparedStatement inserisciBacheca = connection.prepareStatement("Insert into Bacheca(Title, Description, \"Owner\") values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            inserisciBacheca.setString(1, bacheca.getTitle());
            inserisciBacheca.setString(2, bacheca.getDescription());
            inserisciBacheca.setString(3, username);
            inserisciBacheca.executeUpdate();
            ResultSet rs = inserisciBacheca.getGeneratedKeys();
            rs.next();
            bacheca.setId(rs.getInt("ID"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Takes a bacheca as input, checks if it's a default, and if it's not, removes it from the database.
     *
     * @param bacheca the bacheca
     * @return  Returns true if the elimination was successful, otherwise returns false
     */
    public boolean deleteBacheca(Bacheca bacheca) {
        try{
            PreparedStatement checkdefault = connection.prepareStatement("Select * from Bacheca where \"ID\" =?");
            checkdefault.setInt(1, bacheca.getId());
            ResultSet resultSet = checkdefault.executeQuery();
            resultSet.next();
            if (resultSet.getBoolean("isdefault")) {
                return false;
            }
            PreparedStatement rimuoviBacheca = connection.prepareStatement("Delete from Bacheca where \"ID\" = ?");
            rimuoviBacheca.setInt(1, bacheca.getId());
            rimuoviBacheca.executeUpdate();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Takes an updated bacheca as input and applies the changes made to the database.
     *
     * @param bacheca The modified bacheca
     */
    public void editBacheca(Bacheca bacheca) {
        try {
            PreparedStatement editBacheca = connection.prepareStatement("update bacheca set title = ?," +
                                                                            "description = ?  where \"ID\" = ? ");
            editBacheca.setString(1, bacheca.getTitle());
            editBacheca.setString(2, bacheca.getDescription());
            editBacheca.setInt(3, bacheca.getId());
            editBacheca.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
