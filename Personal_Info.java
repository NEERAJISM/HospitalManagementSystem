package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Neeraj on 01-Apr-16.
 */
public class Personal_Info extends AppCompatActivity {

    String username,password;
    DatabaseHelper db;
    TextView name,age,sex,dob,bgroup,utype,city,pincode,mobno,uname,pword;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);
        db = new DatabaseHelper(this);

        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");

        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.age);
        sex = (TextView) findViewById(R.id.sex);
        dob = (TextView) findViewById(R.id.dob);
        bgroup = (TextView) findViewById(R.id.bgroup);
        utype = (TextView) findViewById(R.id.utype);
        city = (TextView) findViewById(R.id.city);
        pincode = (TextView) findViewById(R.id.pincode);
        mobno = (TextView) findViewById(R.id.tv_mno);
        uname = (TextView) findViewById(R.id.username);
        pword = (TextView) findViewById(R.id.password);
        update = (Button) findViewById(R.id.update);

        Cursor y = db.checkduplicates_in_user_credentials(username, password, getResources().getString(R.string.user_credentials));

        if (y.moveToFirst()) {
            String name1 = y.getString(1);
            String name2 = y.getString(2);

            name.setText(name1+" "+name2);
            age.setText(y.getString(3));
            sex.setText(y.getString(6));
            dob.setText(y.getString(5));
            bgroup.setText(y.getString(4));
            utype.setText(y.getString(7));
            city.setText(y.getString(8));
            pincode.setText(y.getString(9));
            mobno.setText(y.getString(10));
            uname.setText(y.getString(12));
            pword.setText(y.getString(11));
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                Cursor y = db.checkduplicates_in_user_credentials(username, password, getResources().getString(R.string.user_credentials));
                Bundle ubundle = new Bundle();
                y.moveToFirst();
                ubundle.putString("first name", y.getString(1));
                ubundle.putString("last name", y.getString(2));
                ubundle.putString("age", y.getString(3));
                ubundle.putString("sex", y.getString(6));
                ubundle.putString("utype", y.getString(7));
                ubundle.putString("city", y.getString(8));
                ubundle.putString("pincode", y.getString(9));
                ubundle.putString("mobno", y.getString(10));
                ubundle.putString("username", y.getString(12));
                ubundle.putString("password", y.getString(11));
                ubundle.putString("dob", y.getString(5));

                i = new Intent(Personal_Info.this, Update_Personal_Info.class);
                i.putExtras(ubundle);
                startActivity(i);
                finish();
            }
        });
    }
}
