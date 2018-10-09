package com.example.liban.balinasofttest.mvp.model;

import android.content.Context;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by liban on 24.08.2018.
 */

public class DBUsers {


    private String mUserName;
    private String mEmail;
    private String mPassword;
    private Context mContext;


    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
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

    public DBUsers(Context context) {
        mContext = context;

    }

    public void insertUsers(final String userName, final String email, final String password) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Users users = realm.createObject(Users.class);
                users.setmName(userName);
                users.setmEmail(email);
                users.setmPassword(password);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(mContext, "Пользователь добавлен", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(mContext, "Пользователь не добавлен: " + error, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void upLoad(final String email, final String password) {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Users> results = realm.where(Users.class)
                .findAll();
        for (Users myUser : results) {
            if (email.equals(myUser.getmEmail()) && password.equals(myUser.getmPassword())) {
                setmUserName(myUser.getmName());
                setmEmail(myUser.getmEmail());
                setmPassword(myUser.getmPassword());
            }
        }
    }
}
