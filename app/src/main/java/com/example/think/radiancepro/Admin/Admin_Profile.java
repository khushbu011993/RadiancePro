package com.example.think.radiancepro.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.think.radiancepro.R;
import com.example.think.radiancepro.Student.Stu_Profile;


public class Admin_Profile extends AppCompatActivity {

    CardView student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__profile);
        student=(CardView)findViewById(R.id.student_profile);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Admin_Profile.this, Stu_Profile.class);
                startActivity(intent);


            }
        });
            }
        }