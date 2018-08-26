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

import com.example.liban.balinasofttest.mvp.model.DataBaseUsersHelper;
import com.example.liban.balinasofttest.mvp.presenter.LoginPresenter;
import com.example.liban.balinasofttest.mvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonLink;
    private SQLiteOpenHelper mSQLiteOpenHelper;
    private AutoCompleteTextView mAutoCompleteTextViewEmail;
    private LoginPresenter mLoginPresenter;
    private TextView mTextViewError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutoCompleteTextViewEmail = findViewById(R.id.email_id);
        mEditTextPassword = findViewById(R.id.password_id);
        mButtonLogin = findViewById(R.id.button_login_id);
        mButtonLink = findViewById(R.id.button_link_to_register);
        mTextViewError = findViewById(R.id.error_email_id);

        mSQLiteOpenHelper = new DataBaseUsersHelper(this);
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
                new ListenerEditText(mAutoCompleteTextViewEmail, this));

    }


    @Override
    public void onClickToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickLogin() {
        String email = mAutoCompleteTextViewEmail.getText().toString().trim();
        String password = mEditTextPassword.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!email.isEmpty() && !password.isEmpty()) {
            if (email.matches(emailPattern)) {
                mTextViewError.setVisibility(View.GONE);
                mLoginPresenter.requestLogin(email, password);

            } else {
                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText("*Например : user@gmail.com");
                Toast.makeText(this, "Email заполнен не корректно", Toast.LENGTH_LONG)
                        .show();
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
    protected void onStart() {
        super.onStart();
        mTextViewError.setVisibility(View.GONE);
    }


}
