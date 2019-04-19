package com.wap.frontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginCompleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_completed);

        TextView message = findViewById(R.id.messageBox);
        if (getIntent().getExtras().getString("username") != null){
            message.setText("You have successfully logged in : "+getIntent().getExtras().getString("username"));
        }
    }
}
