package com.example.astuteresolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Spinner dropdown;
    private Button Facerecognition;
    private Button Maskdetection;
    private Button Socialdistancing;
    private Button logout;
    private FirebaseAuth firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firebase= FirebaseAuth.getInstance();

        Facerecognition = (Button)findViewById(R.id.edbutton1);
        Maskdetection = (Button)findViewById(R.id.edbutton2);
        Socialdistancing =(Button)findViewById(R.id.edbutton);
        dropdown= (Spinner)findViewById(R.id.edspinner);
        logout =(Button)findViewById(R.id.btnlogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebase.signOut();
                finish();
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
            }
        });

        Facerecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,Main5Activity.class));
                finish();
            }
        });


        Maskdetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Socialdistancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        List<String> list = new ArrayList<>();
        list.add("Device Camera");
        list.add("External Camera");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue = parent.getItemAtPosition(position).toString();;
                Toast.makeText(Main2Activity.this, "Selected:",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



}
