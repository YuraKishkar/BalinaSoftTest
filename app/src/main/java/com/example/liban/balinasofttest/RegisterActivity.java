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
                new ListenerEditText(mAutoCompleteTextViewEmail, this));
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
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (!userName.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if (email.matches(emailPattern)) {
                if (password.length() < 4) {
                    mTextViewErrorPassword.setVisibility(View.VISIBLE);
                    mTextViewErrorPassword.setText("Мин. количество знаков 4");
                    Toast.makeText(this, "Пароль заполнен не корректно", Toast.LENGTH_LONG)
                            .show();
                } else {
                    mTextViewErrorEmail.setVisibility(View.GONE);
                    mRegisterPresenter.requestCreate(userName, email, password);
                    Intent intentToLogin = new Intent(this, MainActivity.class);
                    startActivity(intentToLogin);
                }
            } else {
                mTextViewErrorEmail.setVisibility(View.VISIBLE);
                mTextViewErrorEmail.setText("*Например : user@gmail.com");
                Toast.makeText(this, "Email заполнен не корректно", Toast.LENGTH_LONG)
                        .show();
            }


        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextViewErrorEmail.setVisibility(View.GONE);
        mTextViewErrorPassword.setVisibility(View.GONE);
    }
}
