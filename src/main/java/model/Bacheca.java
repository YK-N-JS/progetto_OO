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
     * Returns the bacheca's identifier
     * @return id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        this.todoInBacheca.add(todo);
    }


    /**
     *Removes the todo passed as parameter
     *
     *@param toremove the todo that will be deleted
     */
    public void removeATodo(Todo toremove)
    {
        todoInBacheca.remove(toremove);
    }

    @Override
    public String toString() {
        return title;
    }
}
