package com.whysoserious.neeraj.hospitalmanagementsystem.Patient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.whysoserious.neeraj.hospitalmanagementsystem.R;

/**
 * Created by Neeraj on 17-Mar-16.
 */
public class Patient extends AppCompatActivity {
    Button pinfo, medhistory, newappointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);
        //DEFINING ALL VIEWS
        pinfo = (Button) findViewById(R.id.bpersonalinfo);
        medhistory = (Button) findViewById(R.id.bmedicalhistory);
        newappointment = (Button) findViewById(R.id.bnewappointment);

        pinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Patient.this, PersonalInfo.class);
                startActivity(i);
            }
        });
        medhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Patient.this, MedicalHistory.class);
                startActivity(i);
            }
        });
        newappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Patient.this, NewAppointment.class);
                startActivity(i);
            }
        });
    }
}
