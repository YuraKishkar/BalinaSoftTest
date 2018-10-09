package com.example.liban.balinasofttest;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.liban.balinasofttest.mvp.presenter.RegisterPresenter;

/**
 * Created by liban on 24.09.2018.
 */

public class ListenerEditTextEmailRegister implements TextWatcher {

    private RegisterPresenter mRegisterPresenter;
    private StringBuilder mStringBuilder;
    private TextView mTextViewErrorEmail;

    public ListenerEditTextEmailRegister(RegisterPresenter mRegisterPresenter,
                                         TextView mTextViewErrorEmail) {
        this.mRegisterPresenter = mRegisterPresenter;
        this.mTextViewErrorEmail = mTextViewErrorEmail;
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
            mRegisterPresenter.checkEmailRegister(false);
        } else {
            mTextViewErrorEmail.setVisibility(View.GONE);
            mRegisterPresenter.checkEmailRegister(true);
        }
        if (mStringBuilder.length() == 0) {
            mTextViewErrorEmail.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
