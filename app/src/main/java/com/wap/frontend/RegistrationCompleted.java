package com.wap.frontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrationCompleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_completed);

        TextView message = findViewById(R.id.messageBox);
        if (getIntent().getExtras().getString("username") != null){
            message.setText("You have been successfully registered : "+getIntent().getExtras().getString("username"));
        }
    }
}
