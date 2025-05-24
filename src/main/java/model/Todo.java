package model;

import java.time.LocalDate;

/**
 * The type Todo.
 */
public class Todo {
    private String title;
    private String description;
    private String status;
    private String url_activity;
    private LocalDate complete_by_date;

    /**
     * Instantiates a new Todo.
     */
// needed a way to handle background colour for todos and add images...
    public Todo(){}

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
}
