package com.example.liban.balinasofttest.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.liban.balinasofttest.mvp.model.DataBaseUsersHelper;
import com.example.liban.balinasofttest.mvp.view.LoginView;

/**
 * Created by liban on 24.08.2018.
 */

public class LoginPresenter {
    private LoginView mLoginView;
    private DataBaseUsersHelper mDataBaseUsersHelper;
    private Context mContext;

    public LoginPresenter(LoginView loginView, Context context) {
        mLoginView = loginView;
        mContext = context;
        mDataBaseUsersHelper = new DataBaseUsersHelper(mContext);
    }

    public void clickToRegister() {
        mLoginView.onClickToRegister();
    }


    public void clickLogin() {
        mLoginView.onClickLogin();
    }

    public void requestLogin(String email, String password) {
        mDataBaseUsersHelper.upLoad(email, password);
        if (mDataBaseUsersHelper.getmEmail() != null && mDataBaseUsersHelper.getmUserName() != null) {
            mLoginView.onDetailIntent(mDataBaseUsersHelper.getmEmail(), mDataBaseUsersHelper.getmUserName());
        } else {
            Toast.makeText(mContext, "Пользователь не найден", Toast.LENGTH_LONG).show();
        }
    }
}
