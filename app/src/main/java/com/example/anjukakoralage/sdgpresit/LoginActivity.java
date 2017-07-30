package com.example.anjukakoralage.sdgpresit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText usermail;
    private EditText pass;
    private TextView textInpase;

    private ProgressDialog progressDia;

    private FirebaseAuth FireAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FireAuth = FirebaseAuth.getInstance();

        if(FireAuth.getCurrentUser() != null){
            //home activity here | if already registered user
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        if(FireAuth.getCurrentUser() != null){

            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

        progressDia = new ProgressDialog(this);

        btnRegister = (Button)findViewById(R.id.logRegister);
        usermail = (EditText)findViewById(R.id.logEmail);
        pass = (EditText)findViewById(R.id.logPassword);
        textInpase = (TextView) findViewById(R.id.inPase);


        btnRegister.setOnClickListener(this);
        textInpase.setOnClickListener(this);

    }


    private void registerUser(){
        String email = usermail.getText().toString().trim();
        String password = pass.getText().toString().trim();

            if(TextUtils.isEmpty(email)){

                //  email is empty
                Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();

                return;
            }
            if(TextUtils.isEmpty(password)){

                //  pass is empty
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();

                return;
            }
        progressDia.setMessage("Registering User...");
        progressDia.show();



        FireAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    progressDia.dismiss();

                    Toast.makeText(LoginActivity.this, "Registering Completed",Toast.LENGTH_LONG).show();
                    if(FireAuth.getCurrentUser() != null){
                        //home activity here | if already registered user
                        finish();
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    }



                } else{

                    progressDia.dismiss();
                    Toast.makeText(LoginActivity.this, "Error... Try Again",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }) ;
    }

    @Override
    public void onClick(View v) {

        if(v == btnRegister){

            registerUser();

        }
        if (v == textInpase){
            //finish();
            startActivity(new Intent(this,SingIN.class));
        }

    }
}
