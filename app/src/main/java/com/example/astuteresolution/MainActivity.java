package com.example.astuteresolution;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button ForgotPassword;
    private TextView info;
    private int counter=5;
    private TextView register;
    private FirebaseAuth firebase;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText)findViewById(R.id.etname);
        Password = (EditText)findViewById(R.id.etpassword);
        Login = (Button)findViewById(R.id.button3);
        ForgotPassword=(Button)findViewById(R.id.etfrgtpass);
        info =(TextView)findViewById(R.id.textView4);
        info.setText("No of attempts: 5");


        firebase = FirebaseAuth.getInstance();
        FirebaseUser user = firebase.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }


        register = (TextView)findViewById(R.id.etnewuser);

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Main4Activity.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

    }
    private void validate(String userName,String userPassword){
        firebase.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    checkEmailVerification();
                }else{
                    Toast.makeText(MainActivity.this,"Login was Unsuccessfull",Toast.LENGTH_SHORT).show();
                    counter--;
                    info.setText("No of Attempts:"+ counter);
                    if(counter==0){
                        Login.setEnabled(false);
                    }
                }
            }
        });

    }
    private void checkEmailVerification() {
        FirebaseUser firebaseuser = firebase.getInstance().getCurrentUser();
        Boolean emailflag = firebaseuser.isEmailVerified();

        if (emailflag) {
            finish();
            startActivity(new Intent(MainActivity.this,Main2Activity.class));
        }else{
            Toast.makeText(this,"verify your Email",Toast.LENGTH_SHORT).show();
            firebase.signOut();

        }
    }

}
