package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neeraj on 17-Mar-16.
 */
public class Login extends AppCompatActivity {

    EditText username, password;
    String usernames, passwords;
    Button Bregister, Blogin;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.etpassword);
        Bregister = (Button) findViewById(R.id.bregister);
        Blogin = (Button) findViewById(R.id.blogin);
        dbh = new DatabaseHelper(this);

        Bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernames = username.getText().toString();
                passwords = password.getText().toString();

                Cursor y = dbh.checkduplicates_in_user_credentials(usernames, passwords);

                while(y.moveToNext()){
                    Message.message(Login.this,y.getString(0));
                    Message.message(Login.this,y.getString(1));
                    Message.message(Login.this,y.getString(2));
                    Message.message(Login.this,y.getString(3));
                }

                y.close();
                dbh.close();


                /*if (y.moveToFirst()) {
                    Message.message(Login.this, "Welcome");
                } else {
                    Message.message(Login.this, "No Such User Exists");
                    Message.message(Login.this, "Please Register");
                }*/
            }
        });

    }
}
