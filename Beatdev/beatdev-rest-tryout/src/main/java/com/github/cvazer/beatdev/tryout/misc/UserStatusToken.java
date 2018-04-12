package com.github.cvazer.beatdev.tryout.misc;

import com.github.cvazer.beatdev.tryout.model.User;

public class UserStatusToken {
    private Long id;
    private String oldStatus, newStatus;

    public UserStatusToken() {}

    public UserStatusToken(Long id, String oldStatus, String newStatus) {
        this.id = id;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    public UserStatusToken set(User user, String newStatus){
        if (user.getId()!=0){id = user.getId();}
        if (user.getStatus()!=null){oldStatus = user.getStatus();}
        if (newStatus!=null){
            user.setStatus(newStatus);
            this.newStatus = newStatus;
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}
