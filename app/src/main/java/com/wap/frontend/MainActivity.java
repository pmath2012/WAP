package com.wap.frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wap.frontend.com.wap.frontend.properties.Constants;
import com.wap.frontend.com.wap.restful.frontend.RESTClient;

import java.io.IOException;
import java.util.Properties;

import javax.json.Json;
import javax.json.JsonObject;



public class MainActivity extends AppCompatActivity {

    private String loginUrl;
    private String registerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Properties prop = new Properties();
        try{
            prop.load(getBaseContext().getAssets().open(Constants.PROPERTY));
            loginUrl=prop.getProperty(Constants.LOGIN_URL);
            registerUrl=prop.getProperty(Constants.REGISTER_URL);
        }catch(IOException e){
            //TODO add what to do when properties can't be loaded
            throw new Error("OMG NOoooooo");
        }


        final TextView userTv = findViewById(R.id.username);
        final TextView passTv = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        final TextView errorTv = findViewById(R.id.error_view);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("-----------------------------------");
                System.out.println(loginUrl);
                System.out.println("-----------------------------------");
                JsonObject req =  Json.createObjectBuilder()
                                    .add(Constants.USERNAME, userTv.getText().toString())
                                    .add(Constants.PASSWORD, passTv.getText().toString())
                                    .build();

                new RESTClient(MainActivity.this,LoginCompleted.class, loginUrl, req, errorTv).execute();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObject req = Json.createObjectBuilder()
                        .add(Constants.USERNAME, userTv.getText().toString())
                        .add(Constants.PASSWORD, passTv.getText().toString())
                        .build();
                new RESTClient(MainActivity.this,RegistrationCompleted.class, registerUrl, req, errorTv).execute();
            }
        });
    }

    private void switchActivity(Class target){
        Intent intent = new Intent(this, target);
        startActivity(intent);
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }
}
