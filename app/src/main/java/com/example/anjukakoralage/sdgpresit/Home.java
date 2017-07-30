package com.example.anjukakoralage.sdgpresit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Home extends AppCompatActivity implements View.OnClickListener {


    private ImageButton logout;
    private ImageButton about;
    private ImageButton addNew;
    private ImageButton viewAll;
    private ImageButton update;
    private ImageButton delete;
    private FirebaseAuth firebaseAuth;



/*
public void onclick(View v){


        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
*/


    public void onclickAbout(){
        about = (ImageButton) findViewById(R.id.about_BTN);


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), About.class );
                startActivity(intent);
            }
        });
    }

    public void onclickaddNew(){
        addNew = (ImageButton)findViewById(R.id.addNew_BTN);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddNew.class);
                startActivity(intent);
            }
        });
    }


    public void viewAllInci(){
        viewAll = (ImageButton) findViewById(R.id.viewAll_BTN);


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), viewAll.class);
                startActivity(intent);
            }
        });
    }

    public void editActy(){
        update = (ImageButton) findViewById(R.id.edit_BTN);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), viewAll.class );
                startActivity(intent);
            }
        });
    }

    public void deleteClick(){
        delete = (ImageButton) findViewById(R.id.delete_BTN);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), viewAll.class );
                startActivity(intent);
            }
        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = (ImageButton) findViewById(R.id.logout_BTN);

        logout.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){

            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

      //  FirebaseUser user = firebaseAuth.getCurrentUser();

        onclickAbout();
        onclickaddNew();
        viewAllInci();
        editActy();
        deleteClick();

        }

    @Override
    public void onClick(View v) {
 if (v ==  logout ){
     firebaseAuth.signOut();
     finish();
     startActivity(new Intent(this,SingIN.class));
 }

    }

    @Override
    public void onBackPressed() {
    }
}
