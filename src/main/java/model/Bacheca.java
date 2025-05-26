package model;

import java.util.ArrayList;

/**
 * The type Bacheca.
 */
public class Bacheca {
    private String title;
    private User owner;
    private String description = "- - -";
    private ArrayList<Todo> todoInBacheca = new ArrayList<>();
    private ArrayList<User> usersSharingBacheca = new ArrayList<>();


    /**
     * Instantiates a new Bacheca.
     */
    public Bacheca(){}

    /**
     * Gets title of the bacheca.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title of the bacheca.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets owner of the bacheca.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owner of the bacheca.
     *
     * @param owner the owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets description of the bacheca.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description of the bacheca.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets todo in bacheca.
     *
     * @return the todo in bacheca
     */
    public ArrayList<Todo> getTodoInBacheca() {
        return todoInBacheca;
    }

    /**
     * Sets todo in bacheca.
     *
     * @param todoInBacheca the todo in bacheca
     */
    public void setTodoInBacheca(ArrayList<Todo> todoInBacheca) {
        this.todoInBacheca = todoInBacheca;
    }

    /**
     * Add todo to the list of todos.
     *
     * @param todo the todo
     */
    public void addTodo(Todo todo){
        todoInBacheca.add(todo);
    }

    /**
     * Remove todo.
     *
     * @param index the index of the todo to remove in the list
     */
    public void removeTodo(int index){
        todoInBacheca.remove(index);
    }

    /**
     * Gets users sharing bacheca.
     *
     * @return the users sharing bacheca
     */
    public ArrayList<User> getUsersSharingBacheca() {
        return usersSharingBacheca;
    }

    /**
     * Sets users sharing bacheca.
     *
     * @param usersSharingBacheca the users sharing bacheca
     */
    public void setUsersSharingBacheca(ArrayList<User> usersSharingBacheca) {
        this.usersSharingBacheca = usersSharingBacheca;
    }

    /**
     * Share bacheca.
     *
     * @param user the user
     */
    public void shareBacheca(User user){
        usersSharingBacheca.add(user);
    }

    /**
     * Removes the todo in last position
     */
    public void removeLastTodo()
    {
        todoInBacheca.remove(todoInBacheca.size()-1);
    }

    /**
      Removes the todo passed as parameter

     @param toremove the todo that will be deleted
     */
    public void removeATodo(Todo toremove)
    {
        todoInBacheca.remove(toremove);
    }
}
