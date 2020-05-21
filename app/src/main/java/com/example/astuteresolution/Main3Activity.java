package com.example.astuteresolution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main3Activity extends AppCompatActivity {
    private EditText username;
    private EditText userpassword;
    private EditText useremail;
    private Button submit;
    private FirebaseAuth firebaseAuth;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        setupUI();

        firebaseAuth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload to database
                    String user_email= useremail.getText().toString().trim();
                    String user_pass = userpassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @SuppressLint("ShowToast")
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendEmailVerification();
                            }else{
                                Toast.makeText(Main3Activity.this,"Registration Unsuccessful",Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
            }
        });


    }
    private void setupUI(){
        username = (EditText)findViewById(R.id.rename);
        userpassword =(EditText)findViewById(R.id.repassword);
        useremail=(EditText)findViewById(R.id.edemail);
        submit =(Button)findViewById(R.id.resubmit);
        img=(ImageView)findViewById(R.id.imageView2);

    }

    private Boolean validate(){
        Boolean result = false;
        String name = username.getText().toString();
        String password= userpassword.getText().toString();
        String email= useremail.getText().toString();

        if((name.isEmpty()) || (password.isEmpty()) || (email.isEmpty())){
            Toast.makeText(Main3Activity.this, "Please enter all the details",Toast.LENGTH_SHORT);

        }else{
            result=true;
        }
        return result;

    };


    private void sendEmailVerification(){
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(Main3Activity.this, "Registration Successfull Verification mail sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Main3Activity.this,MainActivity.class));

                    }else{
                        Toast.makeText(Main3Activity.this,"Verification mail not sent",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
