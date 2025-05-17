package model;

import java.util.ArrayList;

public class Bacheca {
    private String title;
    private User owner;
    private String description;
    private ArrayList<Todo> todoInBacheca = new ArrayList<>();
    private ArrayList<User> usersSharingBacheca = new ArrayList<>();



    public Bacheca(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Todo> getTodoInBacheca() {
        return todoInBacheca;
    }

    public void setTodoInBacheca(ArrayList<Todo> todoInBacheca) {
        this.todoInBacheca = todoInBacheca;
    }

    public void addTodo(Todo todo){
        todoInBacheca.add(todo);
    }

    public void removeTodo(int index){
        todoInBacheca.remove(index);
    }

    public ArrayList<User> getUsersSharingBacheca() {
        return usersSharingBacheca;
    }

    public void setUsersSharingBacheca(ArrayList<User> usersSharingBacheca) {
        this.usersSharingBacheca = usersSharingBacheca;
    }

    public void shareBacheca(User user){
        usersSharingBacheca.add(user);
    }
}
