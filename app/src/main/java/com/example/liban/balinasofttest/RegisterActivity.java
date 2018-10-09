package com.example.liban.balinasofttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liban.balinasofttest.mvp.presenter.RegisterPresenter;
import com.example.liban.balinasofttest.mvp.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private AutoCompleteTextView mAutoCompleteTextViewEmail;
    private Button mButtonRegister;
    private Button mButtonLink;
    private RegisterPresenter mRegisterPresenter;
    private TextView mTextViewErrorEmail;
    private TextView mTextViewErrorPassword;
    private boolean mEmail;
    private boolean mPassword;

    public boolean ismEmail() {
        return mEmail;
    }

    public void setmEmail(boolean mEmail) {
        this.mEmail = mEmail;
    }

    public boolean ismPassword() {
        return mPassword;
    }

    public void setmPassword(boolean mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEditTextUserName = findViewById(R.id.name_id);
        mEditTextPassword = findViewById(R.id.password_id);
        mAutoCompleteTextViewEmail = findViewById(R.id.email_id);
        mButtonRegister = findViewById(R.id.button_register_id);
        mButtonLink = findViewById(R.id.button_link_to_login);
        mTextViewErrorEmail = findViewById(R.id.email_error_register_id);
        mTextViewErrorPassword = findViewById(R.id.password_error_register_id);

        mRegisterPresenter = new RegisterPresenter(this, this);

        mButtonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.clickToLogin();
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterPresenter.clickRegister();
            }
        });

        mAutoCompleteTextViewEmail.addTextChangedListener(
                new ListenerEditTextEmailRegister(mRegisterPresenter, mTextViewErrorEmail));

        mEditTextPassword.addTextChangedListener(
                new ListenerEditTextPassword(mRegisterPresenter, mTextViewErrorPassword));
    }

    @Override
    public void onClickToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickRegister() {
        String userName = mEditTextUserName.getText().toString();
        String email = mAutoCompleteTextViewEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();

        if (!userName.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if (ismEmail() && ismPassword()) {
                mRegisterPresenter.requestCreate(userName, email, password);
                Intent intentToLogin = new Intent(this, MainActivity.class);
                startActivity(intentToLogin);
            }

        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onCheckPasswordRegister(boolean password) {
        setmPassword(password);
    }

    @Override
    public void onCheckEmailRegister(boolean email) {
        setmEmail(email);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mTextViewErrorEmail.setVisibility(View.GONE);
        mTextViewErrorPassword.setVisibility(View.GONE);
    }
}
