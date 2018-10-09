package com.example.liban.balinasofttest.mvp.presenter;

import android.content.Context;

import com.example.liban.balinasofttest.mvp.model.DBUsers;
import com.example.liban.balinasofttest.mvp.view.RegisterView;

/**
 * Created by liban on 24.08.2018.
 */

public class RegisterPresenter {
    private RegisterView mRegisterView;
    private DBUsers mDBUsers;
    private Context mContext;

    public RegisterPresenter(RegisterView registerView, Context context){
        mRegisterView = registerView;
        mContext = context;
        mDBUsers = new DBUsers(mContext);
    }


    public void requestCreate(String userName, String email, String password){
        mDBUsers.insertUsers(userName, email, password);
    }


    public void clickToLogin(){
        mRegisterView.onClickToLogin();
    }

    public void clickRegister(){
        mRegisterView.onClickRegister();
    }

    public void checkPasswordRegister(boolean password){
        mRegisterView.onCheckPasswordRegister(password);
    }
    public void checkEmailRegister(boolean email){
        mRegisterView.onCheckEmailRegister(email);
    }
}

