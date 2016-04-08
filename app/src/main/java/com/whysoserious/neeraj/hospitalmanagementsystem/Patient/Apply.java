package com.whysoserious.neeraj.hospitalmanagementsystem.Patient;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.whysoserious.neeraj.hospitalmanagementsystem.DatabaseHelper;
import com.whysoserious.neeraj.hospitalmanagementsystem.Doctor.D_Slot;
import com.whysoserious.neeraj.hospitalmanagementsystem.Doctor.Leaves.Leaves;
import com.whysoserious.neeraj.hospitalmanagementsystem.Doctor.Report_Upload;
import com.whysoserious.neeraj.hospitalmanagementsystem.Doctor.Specialization;
import com.whysoserious.neeraj.hospitalmanagementsystem.Doctor.Staff_View;
import com.whysoserious.neeraj.hospitalmanagementsystem.Feedback;
import com.whysoserious.neeraj.hospitalmanagementsystem.Message;
import com.whysoserious.neeraj.hospitalmanagementsystem.Personal_Info;
import com.whysoserious.neeraj.hospitalmanagementsystem.R;

/**
 * Created by Neeraj on 08-Apr-16.
 */
public class Apply extends AppCompatActivity {
    String username, password, d_username, d_password;
    TextView name, fees, s_start, s_end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);

        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        d_username = bb.getString("d_username");
        d_password = bb.getString("d_password");

        name = (TextView) findViewById(R.id.tv_d_name);
        fees = (TextView) findViewById(R.id.tv_d_fees);
        s_end = (TextView) findViewById(R.id.tv_slot_start);
        s_start = (TextView) findViewById(R.id.tv_slot_end);

        DatabaseHelper dbh = new DatabaseHelper(this);
        Cursor y = dbh.checkduplicates_in_user_credentials(d_username, d_password, getResources().getString(R.string.user_credentials));


        if (y.moveToFirst()) {
            name.setText("Dr.  " + y.getString(1) + " " + y.getString(1));
            DatabaseHelper dbh1 = new DatabaseHelper(this);
            Cursor z = dbh1.checkduplicates_in_user_credentials(d_username, d_password, getResources().getString(R.string.doctor_slot));
            if (z.moveToFirst()) {
                s_start.setText(z.getString(3));
                s_end.setText(z.getString(4));
            }
        }
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.b_apply:

                Message.message(Apply.this, "Your Application has been Sent");
                finish();
                break;
        }
    }
}
