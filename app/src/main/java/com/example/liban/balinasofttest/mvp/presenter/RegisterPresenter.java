package com.example.liban.balinasofttest.mvp.presenter;

import android.content.Context;

import com.example.liban.balinasofttest.mvp.model.DataBaseUsersHelper;
import com.example.liban.balinasofttest.mvp.view.RegisterView;

/**
 * Created by liban on 24.08.2018.
 */

public class RegisterPresenter {
    private RegisterView mRegisterView;
    private DataBaseUsersHelper mDataBaseUsersHelper;
    private Context mContext;

    public RegisterPresenter(RegisterView registerView, Context context){
        mRegisterView = registerView;
        mContext = context;
        mDataBaseUsersHelper = new DataBaseUsersHelper(mContext);
    }


    public void requestCreate(String userName, String email, String password){
        mDataBaseUsersHelper.insertUsers(userName, email, password);
    }


    public void clickToLogin(){
        mRegisterView.onClickToLogin();
    }

    public void clickRegister(){
        mRegisterView.onClickRegister();
    }
}
