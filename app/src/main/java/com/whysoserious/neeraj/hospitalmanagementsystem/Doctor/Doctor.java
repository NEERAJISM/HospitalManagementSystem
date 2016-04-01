package com.whysoserious.neeraj.hospitalmanagementsystem.Doctor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.whysoserious.neeraj.hospitalmanagementsystem.DatabaseHelper;
import com.whysoserious.neeraj.hospitalmanagementsystem.Desktop_Admin.Desktop_Admin;
import com.whysoserious.neeraj.hospitalmanagementsystem.Feedback;
import com.whysoserious.neeraj.hospitalmanagementsystem.Message;
import com.whysoserious.neeraj.hospitalmanagementsystem.Patient.Patient;
import com.whysoserious.neeraj.hospitalmanagementsystem.Personal_Info;
import com.whysoserious.neeraj.hospitalmanagementsystem.R;
import com.whysoserious.neeraj.hospitalmanagementsystem.Staff_Member.Staff_Member;

/**
 * Created by Neeraj on 17-Mar-16.
 */
public class Doctor extends AppCompatActivity {

    String username,password;
    DatabaseHelper dbh;
    TextView dname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        dbh = new DatabaseHelper(this);
        dname = (TextView) findViewById(R.id.tv_d_name);


        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");

        Cursor y = dbh.checkduplicates_in_user_credentials(username, password);

        if (y.moveToFirst()) {
            String name = y.getString(1);
            dname.setText(name);
        }
    }

    public void onClick(View view){

        Intent i;
        Bundle b = new Bundle();
        b.putString("username",username);
        b.putString("password",password);

        switch (view.getId())
        {
            case R.id.b_d_info:
                i = new Intent(Doctor.this, Personal_Info.class);
                break;
            case R.id.b_add_specialization:
                i = new Intent(Doctor.this, Specialization.class);
                break;
            case R.id.b_d_leaves:
                i = new Intent(Doctor.this, Leaves.class);
                break;
            case R.id.b_d_upload_report:
                i = new Intent(Doctor.this, Report_Upload.class);
                break;
            case R.id.b_d_staff_assigned:
                i = new Intent(Doctor.this, Staff_View.class);
                break;
            default:
                i = new Intent(Doctor.this, Feedback.class);
                break;
        }

        i.putExtras(b);
        startActivity(i);
    }
}