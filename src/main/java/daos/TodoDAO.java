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

    public ArrayList<Todo> getTodoInBacheca(Bacheca bacheca) {
        try{
            ArrayList<Todo> todoList = new ArrayList<Todo>();
            PreparedStatement recuperaTodo = connection.prepareStatement("Select todo.\"ID\", todo.title, todo.URL, todo.description, todo.\"Owner\", todo.icon," +
                    " todo.color, todo.Complete_By_Date, todo.completed From Placement JOIN todo ON Placement.IDToDo = todo.\"ID\" where Placement.IDBacheca = ?");
            recuperaTodo.setInt(1, bacheca.getId());
            ResultSet resultSet= recuperaTodo.executeQuery();
            while(resultSet.next()){
                        todoList.add(new Todo(resultSet.getInt("\"ID\""),
                        resultSet.getString("Title"),
                        resultSet.getString("URL"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Icon"),
                        resultSet.getInt("Color"),
                        resultSet.getDate("Complete_By_Date").toLocalDate(),
                        resultSet.getBoolean("Completed")));
            }
            connection.close();
            return todoList;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void removeTodo(Todo todo){
        try{
            PreparedStatement rimuoviTodo = connection.prepareStatement("Delete From Todo Where Todo.\"ID\" = ?");
            rimuoviTodo.setInt(1, todo.getID());

            connection.close();
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
            aggiungiTodo.executeQuery();

            PreparedStatement aggiungiPlacement = connection.prepareStatement("Insert INTO Placement(IDBacheca, IDToDo) Values(?, ?)");
            aggiungiPlacement.setInt(1, bacheca.getId());
            aggiungiPlacement.setInt(2, aggiungiTodo.getGeneratedKeys().getInt("IDTodo"));
            aggiungiPlacement.executeQuery();

            connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void shareTodo(int idTodo, String user){
        try {
            PreparedStatement query = connection.prepareStatement("call condividitodo(?, ?)");
            query.setInt(1, idTodo);
            query.setString(2, user);

            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
