package com.example.liban.balinasofttest.mvp.model;

import io.realm.RealmObject;

/**
 * Created by liban on 09.10.2018.
 */

public class Users extends RealmObject {
    private String mName;
    private String mEmail;
    private  String mPassword;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
