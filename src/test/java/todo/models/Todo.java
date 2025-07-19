package todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Todo {
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    private String item;
    @JsonProperty("_id")
    private String id;
    private String userID;
    private String createdAt;
    @JsonProperty("__v")
    private String v;

    public Todo(Boolean isCompleted, String item) {
        this.isCompleted = isCompleted;
        this.item = item;
    }

    public Todo(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Todo(String item) {
        this.item = item;
    }


    public Todo() {
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
