package com.example.liban.balinasofttest;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liban.balinasofttest.mvp.presenter.RegisterPresenter;

/**
 * Created by liban on 17.09.2018.
 */

public class ListenerEditTextPassword implements TextWatcher {

    private RegisterPresenter mRegisterPresenter;
    private TextView mTextViewErrorPassword;
    private StringBuilder mStringBuilder;

    public ListenerEditTextPassword(RegisterPresenter mRegisterPresenter, TextView mTextViewErrorPassword) {
        this.mRegisterPresenter = mRegisterPresenter;
        this.mTextViewErrorPassword = mTextViewErrorPassword;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mStringBuilder = new StringBuilder();
        mStringBuilder.append(String.valueOf(charSequence));
        if (mStringBuilder.length() < 4) {
            mTextViewErrorPassword.setVisibility(View.VISIBLE);
            mTextViewErrorPassword.setText("Мин. количество знаков 4");
            mRegisterPresenter.checkPasswordRegister(false);
        } else {
            mTextViewErrorPassword.setVisibility(View.GONE);
            mRegisterPresenter.checkPasswordRegister(true);
        }
        if (mStringBuilder.length() == 0) {
            mTextViewErrorPassword.setVisibility(View.GONE);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
