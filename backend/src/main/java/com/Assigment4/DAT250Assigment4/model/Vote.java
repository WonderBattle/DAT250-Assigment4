package com.Assigment4.DAT250Assigment4.model;

public class Vote {
    private String id;
    private String publishedAt;
    private User user;        // Vote is made by a User
    private VoteOption voteOption; // Vote is for an Option

    public Vote() {}

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }
    public void setVoteOption(VoteOption voteOption) {
        this.voteOption = voteOption;
    }
}