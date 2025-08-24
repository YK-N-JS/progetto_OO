package daos;

import controller.Controller;
import databaseConnection.ConnessioneDatabase;
import model.Bacheca;
import model.Todo;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class TodoDAO {
    private Connection connection;
    public TodoDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Todo> getTodoInBachecaAlphabetical(Bacheca bacheca) {
        try{
            ArrayList<Todo> todoList = new ArrayList<Todo>();
            PreparedStatement recuperaTodo = connection.prepareStatement("select * from sortalphabetical(?)");
            recuperaTodo.setInt(1, bacheca.getId());
            ResultSet resultSet= recuperaTodo.executeQuery();
            while(resultSet.next()){
                        todoList.add(new Todo(resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("URL"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Icon"),
                        resultSet.getInt("Color"),
                        resultSet.getDate("Complete_By_Date").toLocalDate(),
                        resultSet.getBoolean("Completed")));
            }
            return todoList;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Todo> getTodoInBachecaByDate(Bacheca bacheca) {
        try{
            ArrayList<Todo> todoList = new ArrayList<Todo>();
            PreparedStatement recuperaTodo = connection.prepareStatement("select * from sortbydate(?)");
            recuperaTodo.setInt(1, bacheca.getId());
            ResultSet resultSet= recuperaTodo.executeQuery();
            while(resultSet.next()){
                todoList.add(new Todo(resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("URL"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Icon"),
                        resultSet.getInt("Color"),
                        resultSet.getDate("Complete_By_Date").toLocalDate(),
                        resultSet.getBoolean("Completed")));
            }
            return todoList;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void removeTodo(Todo todo, Bacheca bacheca) {
        try{
            PreparedStatement rimuoviTodo = connection.prepareStatement("Delete From placement Where idtodo = ? and idbacheca = ?");
            rimuoviTodo.setInt(1, todo.getID());
            rimuoviTodo.setInt(2, bacheca.getId());
            rimuoviTodo.execute();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addTodo(Bacheca bacheca, Todo todo, String user){
        try{
            PreparedStatement aggiungiTodo = connection.prepareStatement("Insert INTO ToDo(Title, URL, Description, \"Owner\", Complete_By_Date)" +
                    "Values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            aggiungiTodo.setString(1, todo.getTitle());
            aggiungiTodo.setString(2, todo.getUrl_activity());
            aggiungiTodo.setString(3, todo.getDescription());
            aggiungiTodo.setString(4, user);
            aggiungiTodo.setDate(5, Date.valueOf(todo.getComplete_by_date()));
            aggiungiTodo.executeUpdate();
            ResultSet rs = aggiungiTodo.getGeneratedKeys();
            rs.next();
            todo.setID(rs.getInt("ID"));

            PreparedStatement aggiungiPlacement = connection.prepareStatement("Insert INTO Placement(IDBacheca, IDToDo) Values(?, ?)");
            aggiungiPlacement.setInt(1, bacheca.getId());
            aggiungiPlacement.setInt(2, rs.getInt("ID"));
            aggiungiPlacement.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void shareTodo(int idTodo, String user){
        try {
            PreparedStatement query = connection.prepareStatement("call condividitodo(?, ?)");
            query.setInt(2, idTodo);
            query.setString(1, user);
            query.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editTodo(Todo todo){
        try {
        PreparedStatement editTodo = connection.prepareStatement("update todo set title = ?," +
                " description = ?, url = ?, icon = ?, color = ?, complete_by_date = ?, completed = ?" +
                " where \"ID\" = ?");
        editTodo.setString(1, todo.getTitle());
        editTodo.setString(2, todo.getDescription());
        editTodo.setString(3, todo.getUrl_activity());
        editTodo.setInt(4, todo.getIconID());
        editTodo.setInt(5, todo.getColor());
        editTodo.setDate(6, Date.valueOf(todo.getComplete_by_date()));
        editTodo.setBoolean(7, todo.getStatus());
        editTodo.setInt(8, todo.getID());

        editTodo.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void spostaTodo(int todoid, int origin, int destination) {
        try {
            PreparedStatement spostaTodo = connection.prepareStatement("call movetodo(?, ?, ?)");
            spostaTodo.setInt(1, todoid);
            spostaTodo.setInt(2, origin);
            spostaTodo.setInt(3, destination);
            spostaTodo.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



}
