package model;

import java.time.LocalDate;

public class Todo {
    private String title;
    private String description;
    private String status;
    private String url_activity;
    private LocalDate complete_by_date;
    // needed a way to handle background colour for todos and add images...
    public Todo(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl_activity() {
        return url_activity;
    }

    public void setUrl_activity(String url_activity) {
        this.url_activity = url_activity;
    }

    public LocalDate getComplete_by_date() {
        return complete_by_date;
    }

    public void setComplete_by_date(LocalDate complete_by_date) {
        this.complete_by_date = complete_by_date;
    }
}
