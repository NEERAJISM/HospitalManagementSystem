package com.whysoserious.neeraj.hospitalmanagementsystem.Desktop_Admin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.whysoserious.neeraj.hospitalmanagementsystem.DatabaseHelper;
import com.whysoserious.neeraj.hospitalmanagementsystem.Personal_Info;
import com.whysoserious.neeraj.hospitalmanagementsystem.R;

/**
 * Created by Neeraj on 20-Mar-16.
 */
public class Desktop_Admin extends AppCompatActivity {
    String username,password;
    DatabaseHelper dbh;
    TextView daname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desktop_admin);

        dbh = new DatabaseHelper(this);
        daname = (TextView) findViewById(R.id.tv_da_name);


        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");

        Cursor y = dbh.checkduplicates_in_user_credentials(username, password);

        if (y.moveToFirst()) {
            String name = y.getString(1);
            daname.setText(name);
        }
    }

    public void onClick(View view){

        Intent i;
        Bundle b = new Bundle();
        b.putString("username",username);
        b.putString("password",password);

        switch (view.getId())
        {
            case R.id.b_da_info:
                i = new Intent(Desktop_Admin.this, Personal_Info.class);
                break;
            default:
                i = new Intent(Desktop_Admin.this, Personal_Info.class);
                break;
        }

        i.putExtras(b);
        startActivity(i);
    }
}


