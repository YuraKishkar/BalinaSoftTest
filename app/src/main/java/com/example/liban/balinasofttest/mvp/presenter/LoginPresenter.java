package com.example.liban.balinasofttest.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.liban.balinasofttest.mvp.model.DBUsers;
import com.example.liban.balinasofttest.mvp.view.LoginView;

/**
 * Created by liban on 24.08.2018.
 */

public class LoginPresenter {
    private LoginView mLoginView;
    private DBUsers mDBUsers;
    private Context mContext;

    public LoginPresenter(LoginView loginView, Context context) {
        mLoginView = loginView;
        mContext = context;
        mDBUsers = new DBUsers(mContext);
    }

    public void clickToRegister() {
        mLoginView.onClickToRegister();
    }


    public void clickLogin() {
        mLoginView.onClickLogin();
    }

    public void requestLogin(String email, String password) {
        mDBUsers.upLoad(email, password);
        if (mDBUsers.getmEmail() != null && mDBUsers.getmUserName() != null) {
            mLoginView.onDetailIntent(mDBUsers.getmEmail(), mDBUsers.getmUserName());
        } else {
            Toast.makeText(mContext, "Пользователь не найден", Toast.LENGTH_LONG).show();
        }
    }

    public void checkEmailLogin(boolean email){
        mLoginView.onCheckEmailLogin(email);
    }
}
