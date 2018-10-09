package com.example.liban.balinasofttest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.liban.balinasofttest.mvp.presenter.LoginPresenter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liban on 26.08.2018.
 */

public class ListenerEditTextEmailLogin implements TextWatcher {

    private AutoCompleteTextView mAutoCompleteTextViewEmail;
    private Context mContext;
    private StringBuilder mStringBuilder;
    private TextView mTextViewErrorEmail;
    private LoginPresenter mLoginPresenter;


    public ListenerEditTextEmailLogin(AutoCompleteTextView mAutoCompleteTextViewEmail, Context mContext,
                                      TextView mTextViewErrorEmail, LoginPresenter mLoginPresenter) {
        this.mAutoCompleteTextViewEmail = mAutoCompleteTextViewEmail;
        this.mContext = mContext;
        this.mTextViewErrorEmail = mTextViewErrorEmail;
        this.mLoginPresenter = mLoginPresenter;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        mStringBuilder = new StringBuilder();
        mStringBuilder.append(String.valueOf(charSequence));

        if (!mStringBuilder.toString().trim().matches(emailPattern)) {
            mTextViewErrorEmail.setVisibility(View.VISIBLE);
            mTextViewErrorEmail.setText("*Например : user@gmail.com");
            mLoginPresenter.checkEmailLogin(false);
        } else {
            mTextViewErrorEmail.setVisibility(View.GONE);
            mLoginPresenter.checkEmailLogin(true);
        }
        if (mStringBuilder.length() == 0) {
            mTextViewErrorEmail.setVisibility(View.GONE);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
