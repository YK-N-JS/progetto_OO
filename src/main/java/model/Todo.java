package model;

import java.time.LocalDate;

/**
 * The type Todo.
 */
public class Todo {
    private int id;
    private String title;
    private String description;
    private String status = "to complete";
    private String url_activity;
    private LocalDate complete_by_date;
    private boolean completed;
    private int icon;
    private int color;
    private User owner;

    /**
     * Instantiates a new todo. (without parameters)
     */
    public Todo(){}

    /**
     * Instantiates a new todo. (with parameters)
     * @param id the todo's identifier
     * @param title the todo's title
     * @param url_activity the url associated with the todo
     * @param description the todo's description
     * @param owner the todo's creator
     * @param icon the todo's icon index
     * @param color the todo's color index
     * @param complete_by_date the todo's expiration date
     * @param completed a boolean that indicates whether the todo has been completed
     */
    public Todo(int id, String title, String url_activity, String description, User owner, int icon, int color, LocalDate complete_by_date, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.url_activity = url_activity;
        this.owner = owner;
        this.icon = icon;
        this.color = color;
        this.complete_by_date = complete_by_date;
        this.completed = completed;
    }

    /**
     * Returns the todo's identifier
     * @return int
     */
    public int getID(){
        return id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets url activity.
     *
     * @return the url activity
     */
    public String getUrl_activity() {
        return url_activity;
    }

    /**
     * Sets url activity.
     *
     * @param url_activity the url activity
     */
    public void setUrl_activity(String url_activity) {
        this.url_activity = url_activity;
    }

    /**
     * Gets complete by date.
     *
     * @return the complete by date
     */
    public LocalDate getComplete_by_date() {
        return complete_by_date;
    }

    /**
     * Sets complete by date.
     *
     * @param complete_by_date the complete by date
     */
    public void setComplete_by_date(LocalDate complete_by_date) {
        this.complete_by_date = complete_by_date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public User getOwner (){
        return owner;
    }
}
