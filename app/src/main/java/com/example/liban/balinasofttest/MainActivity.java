package com.example.liban.balinasofttest;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liban.balinasofttest.mvp.presenter.LoginPresenter;
import com.example.liban.balinasofttest.mvp.view.LoginView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements LoginView {
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonLink;
    private SQLiteOpenHelper mSQLiteOpenHelper;
    private AutoCompleteTextView mAutoCompleteTextViewEmail;
    private LoginPresenter mLoginPresenter;
    private TextView mTextViewError;
    private boolean mEmail;

    public boolean ismEmail() {
        return mEmail;
    }

    public void setmEmail(boolean mEmail) {
        this.mEmail = mEmail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        mAutoCompleteTextViewEmail = findViewById(R.id.email_id);
        mEditTextPassword = findViewById(R.id.password_id);
        mButtonLogin = findViewById(R.id.button_login_id);
        mButtonLink = findViewById(R.id.button_link_to_register);
        mTextViewError = findViewById(R.id.error_email_id);


        mLoginPresenter = new LoginPresenter(this, this);

        mButtonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.clickToRegister();
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginPresenter.clickLogin();
            }
        });

        mAutoCompleteTextViewEmail.addTextChangedListener(
                new ListenerEditTextEmailLogin(mAutoCompleteTextViewEmail, this,
                        mTextViewError, mLoginPresenter));

    }


    @Override
    public void onClickToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickLogin() {
        String email = mAutoCompleteTextViewEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();


        if (!email.isEmpty() && !password.isEmpty()) {
            if (ismEmail()) {
                mLoginPresenter.requestLogin(email, password);
            }

        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onDetailIntent(String email, String userName) {
        Intent intent = new Intent(this, DetailUser.class);
        intent.putExtra(DetailUser.EMAIL, email);
        intent.putExtra(DetailUser.USER_NAME, userName);
        startActivity(intent);
    }

    @Override
    public void onCheckEmailLogin(boolean email) {
        setmEmail(email);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextViewError.setVisibility(View.GONE);
    }


}
