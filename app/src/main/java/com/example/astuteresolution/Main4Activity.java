package com.example.astuteresolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Main4Activity extends AppCompatActivity {
    private EditText passemail;
    private Button reset;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        passemail =(EditText)findViewById(R.id.etemail);
        reset =(Button)findViewById(R.id.etreset);
        firebaseAuth =FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passemail.getText().toString().trim();
                if(useremail.equals("")){
                    Toast.makeText(Main4Activity.this, " please enter your email id",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Main4Activity.this,"Reset email sent  ",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Main4Activity.this,  MainActivity.class));

                            }else{
                                Toast.makeText(Main4Activity.this,"error in sending email ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
