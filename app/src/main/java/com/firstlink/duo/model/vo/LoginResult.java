package com.firstlink.duo.model.vo;

import com.firstlink.duo.model.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wzq on 15/5/19.
 */
public class LoginResult {

    @SerializedName("regist_mark")
    private int mark;

    private User user;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
