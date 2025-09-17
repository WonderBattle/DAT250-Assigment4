package com.Assigment4.DAT250Assigment4.model;

import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private String id;
    private String username;
    private String email;
    private List<Poll> createdPolls = new ArrayList<>(); // User creates polls
    private List<Vote> votes = new ArrayList<>();        // User makes votes

    public User() {}

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Add relationship getters/setters
    @JsonIgnore
    public List<Poll> getCreatedPolls() {
        return createdPolls;
    }
    public void setCreatedPolls(List<Poll> createdPolls) {
        this.createdPolls = createdPolls;
    }

    @JsonIgnore
    public List<Vote> getVotes() {
        return votes;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
