package model;

import java.util.ArrayList;


/**
 * The type Bacheca.
 */
public class Bacheca {
    private int id;
    private String title;
    private String description = "- - -";
    private boolean isDefault;
    private ArrayList<Todo> todoInBacheca = new ArrayList<>();


    /**
     * Instantiates a new bacheca (no parameters).
     */
    public Bacheca(){}


    /**
     * Instantiates a new bacheca (with parameters).
     *
     * @param id The bacheca's identifier
     * @param title The bacheca's title
     * @param description The bacheca's description
     * @param isDefault A boolean that indicates whether the bacheca is a default
     */
    public Bacheca(int id, String title, String description, boolean isDefault) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDefault = isDefault;
    }


    /**
     * Returns the bacheca's identifier.
     *
     * @return The identifier
     */
    public int getId() {
        return id;
    }


    /**
     * takes an integer as input and sets it as the bacheca's identifier.
     *
     * @param id The integer
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the title of the bacheca.
     *
     * @return The title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Sets the title of the bacheca.
     *
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Gets the description of the bacheca.
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description of the bacheca.
     *
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Take as array list of ToDo's and sets it as the todo in bacheca.
     *
     * @param todoInBacheca The todo in bacheca
     */
    public void setTodoInBacheca(ArrayList<Todo> todoInBacheca) {
        this.todoInBacheca = todoInBacheca;
    }


    /**
     * Adds a todo to the list of todos.
     *
     * @param todo The todo
     */
    public void addTodo(Todo todo){
        this.todoInBacheca.add(todo);
    }


    /**
     * Removes the todo passed as parameter from its assigned todoInBacheca array list.
     *
     * @param toRemove The todo that will be deleted
     */
    public void removeATodo(Todo toRemove){
        todoInBacheca.remove(toRemove);
    }


    /**
     * Overrides the default toString method in java.lang.Object.
     *
     * @return the title
     */
    @Override
    public String toString() {
        return title;
    }
}
