package com.whysoserious.neeraj.hospitalmanagementsystem;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.whysoserious.neeraj.hospitalmanagementsystem.Desktop_Admin.Desktop_Admin;
import com.whysoserious.neeraj.hospitalmanagementsystem.Doctor.Doctor;
import com.whysoserious.neeraj.hospitalmanagementsystem.Patient.Patient;
import com.whysoserious.neeraj.hospitalmanagementsystem.Staff_Member.Staff_Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naveen84 on 09-04-2016.
 */
public class Update_Personal_Info extends AppCompatActivity {

    DatabaseHelper dbh;
    String usernames, passwords, utypes, dobs;
    EditText fname, lname, age, city, pincode, mobno, yy, dd;
    String fnames, lnames, ages, citys, pincodes, mobnos, sexs, mms, yys, dds, bgroups;
    TextView utype, username, password;
    Button update;
    Spinner mm, sex, bgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_personal_info);
        Bundle ubundle = getIntent().getExtras();
        dbh = new DatabaseHelper(this);

        fname = (EditText) findViewById(R.id.etupdatefname);
        lname = (EditText) findViewById(R.id.etupdatelname);
        age = (EditText) findViewById(R.id.etupdateage);
        utype = (TextView) findViewById(R.id.tutype);
        city = (EditText) findViewById(R.id.etupdatecity);
        pincode = (EditText) findViewById(R.id.etupdatepin);
        mobno = (EditText) findViewById(R.id.etupdatemobile);
        username = (TextView) findViewById(R.id.tupdateusername);
        password = (TextView) findViewById(R.id.tupdatepassword);
        update = (Button) findViewById(R.id.bupdate);
        mm = (Spinner) findViewById(R.id.spinnermonth);
        sex = (Spinner) findViewById(R.id.spinnersex);
        bgroup = (Spinner) findViewById(R.id.spinnerbgroup);
        dd = (EditText) findViewById(R.id.etupdatedd);
        yy = (EditText) findViewById(R.id.etupdateyy);

        final List<String> sexc = new ArrayList<>();
        sexc.add("Male");
        sexc.add("Female");

        List<String> bgroupc = new ArrayList<>();
        bgroupc.add("A+");
        bgroupc.add("A-");
        bgroupc.add("B+");
        bgroupc.add("B-");
        bgroupc.add("O+");
        bgroupc.add("O-");
        bgroupc.add("AB+");
        bgroupc.add("AB-");

        List<String> months = new ArrayList<>();
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");

        ArrayAdapter<String> adapterm = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        ArrayAdapter<String> adapters = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bgroupc);
        ArrayAdapter<String> adapterb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sexc);

        adapterm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mm.setAdapter(adapterm);
        sex.setAdapter(adapters);
        bgroup.setAdapter(adapterb);

        fnames = ubundle.getString("first name");
        lnames = ubundle.getString("last name");
        ages = ubundle.getString("age");
        utypes = ubundle.getString("utype");
        citys = ubundle.getString("city");
        pincodes = ubundle.getString("pincode");
        mobnos = ubundle.getString("mobno");
        usernames = ubundle.getString("username");
        passwords = ubundle.getString("password");
        dobs = ubundle.getString("dob");

        fname.setText(fnames);
        lname.setText(lnames);
        age.setText(ages);
        utype.setText(utypes);
        city.setText(citys);
        pincode.setText(pincodes);
        mobno.setText(mobnos);
        username.setText(usernames);
        password.setText(passwords);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnames = lname.getText().toString();
                fnames = fname.getText().toString();
                ages = age.getText().toString();
                dds = dd.getText().toString();
                yys = yy.getText().toString();
                citys = city.getText().toString();
                pincodes = pincode.getText().toString();
                usernames = username.getText().toString();
                passwords = password.getText().toString();
                mobnos = mobno.getText().toString();
                utypes = utype.getText().toString();
                mms = mm.getSelectedItem().toString();
                sexs = sex.getSelectedItem().toString();
                bgroups = bgroup.getSelectedItem().toString();

                if (fnames.equals("") || lnames.equals("") || ages.equals("") || dds.equals("") ||
                        yys.equals("") || citys.equals("") || pincodes.equals("") || username.equals("") ||
                        password.equals("") || mobnos.equals("")) {
                    Message.message(Update_Personal_Info.this, "Please Fill in all your Details");
                } else {

                    //CHECK WHETHER THE ENTRY ALREADY EXISTS
                    Cursor y = dbh.checkduplicates_in_user_credentials(usernames, passwords, getResources().getString(R.string.user_credentials));
                    if (y.moveToFirst()) {

                        //SETUP DATABASE QUERY
                        if (dds.length() == 1)
                            dds = "0" + dds;
                        dobs = dds + " " + mms + " " + yys;

                        boolean b = dbh.update_Personal_Info(fnames, lnames, ages, dobs, citys, pincodes, mobnos, sexs, bgroups, usernames, passwords);
                        if (b) {
                            Intent i;
                            Bundle bb = new Bundle();
                            bb.putString("username", usernames);
                            bb.putString("password", passwords);
                            bb.putString("user_type", utypes);

                            if (utypes.equals("Patient")) {
                                i = new Intent(Update_Personal_Info.this, Patient.class);
                            } else if (utypes.equals("Doctor")) {
                                i = new Intent(Update_Personal_Info.this, Doctor.class);
                            } else if (utypes.equals("Staff Member")) {
                                i = new Intent(Update_Personal_Info.this, Staff_Member.class);
                            } else {
                                i = new Intent(Update_Personal_Info.this, Desktop_Admin.class);
                            }

                            i.putExtras(bb);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            }
        });

    }
}
