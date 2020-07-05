package io.techstack.dto;

import com.google.common.base.MoreObjects;

public class User {
    private String  userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("User name: ", userName)
                          .add("Password: ", password)
                          .toString();
    }
}
