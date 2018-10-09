package com.example.liban.balinasofttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailUser extends AppCompatActivity {
    public final static String EMAIL = "email";
    public final static String USER_NAME = "userName";

    private TextView mTextViewEmail;
    private Button mButton;
    private TextView mTextViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        mTextViewEmail = findViewById(R.id.email);
        mButton = findViewById(R.id.button_logout_id);
        mTextViewName = findViewById(R.id.name);

        Intent intent = getIntent();
        String email = (String) intent.getExtras().get(EMAIL);
        String userName = (String) intent.getExtras().get(USER_NAME);
        mTextViewEmail.setText(email);
        mTextViewName.setText(userName);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(DetailUser.this, MainActivity.class);
                startActivity(intentBack);
            }
        });
    }
}
