package model;
import java.time.LocalDate;

/**
 * The class type Todo.
 */
public class Todo {
    private int id;
    private String title;
    private String description;
    private boolean status = false;
    private String urlActivity;
    private LocalDate completeByDate;
    private int iconID;
    private int color;


    /**
     * Instantiates a new todo. (without parameters)
     */
    public Todo(){}


    /**
     * Instantiates a new todo. (with parameters)
     *
     * @param id the todo's identifier
     * @param title the todo's title
     * @param urlActivity the url associated with the todo
     * @param description the todo's description
     * @param icon the todo's icon index
     * @param color the todo's color index
     * @param completeByDate the todo's expiration date
     * @param completed a boolean that indicates whether the todo has been completed
     */
    public Todo(int id, String title, String urlActivity, String description, int icon, int color, LocalDate completeByDate, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.urlActivity = urlActivity;
        this.iconID = icon;
        this.color = color;
        this.completeByDate = completeByDate;
        this.status = completed;
    }


    /**
     * Returns the todo's identifier
     * @return int
     */
    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
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
     * Gets completion status.
     *
     * @return the status
     */
    public boolean getStatus() {
        return status;
    }


    /**
     * Sets completion status.
     *
     * @param status the status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }


    /**
     * Gets url activity.
     *
     * @return the url activity
     */
    public String getUrl_activity() {
        return urlActivity;
    }


    /**
     * Sets url activity.
     *
     * @param urlActivity the url activity
     */
    public void setUrl_activity(String urlActivity) {
        this.urlActivity = urlActivity;
    }


    /**
     * Gets complete_by_date value.
     *
     * @return the completeByDate
     */
    public LocalDate getComplete_by_date() {
        return completeByDate;
    }


    /**
     * Sets complete_by_date value.
     *
     * @param completeByDate the complete by date
     */
    public void setComplete_by_date(LocalDate completeByDate) {
        this.completeByDate = completeByDate;
    }


    /**
     * Gets the icon's identifying number.
     *
     * @return the icon's ID
     */
    public int getIconID() {
        return iconID;
    }


    /**
     * Sets the icon's identifying number.
     *
     * @param icon The identifying number
     */
    public void setIcon(int icon) {
        this.iconID = icon;
    }


    /**
     * Gets the color's identifying number.
     *
     * @return the color's identifying number
     */
    public int getColor() {
        return color;
    }


    /**
     * Sets the color's identifying number.
     *
     * @param color The identifying number
     */
    public void setColor(int color) {
        this.color = color;
    }
}