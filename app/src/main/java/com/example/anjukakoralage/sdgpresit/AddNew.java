package com.example.anjukakoralage.sdgpresit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class AddNew extends AppCompatActivity implements View.OnClickListener {
 //
//    private ImageButton addNewInci;
//
//    private EditText IDInci;
//    private EditText reporterName;
//    private EditText type;
//    private EditText responsePerson;
//    private EditText Note;
//    private EditText savedate;
//    private EditText savetime;
//    private FirebaseAuth firebaseAuth;
//
//    private StorageReference reference;
//    private DatabaseReference mdatabase;
//
//    private ProgressDialog mProgress;
//
//    private DatabaseReference databaseReference;
//
//
//    Spinner typeSpinner;
//    ArrayAdapter<CharSequence> adapter;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        addNewInci = (ImageButton)findViewById(R.id.addNewIncident);
//
//        IDInci = (EditText) findViewById(R.id.ID);
//        reporterName = (EditText) findViewById(R.id.reporterName);
//        responsePerson = (EditText) findViewById(R.id.responser);
//        Note = (EditText) findViewById(R.id.note);
//        savedate = (EditText) findViewById(R.id.date);
//        savetime = (EditText) findViewById(R.id.time);
//        mProgress = new ProgressDialog(this);
//        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        addNewInci.setOnClickListener(this);
//
//
//
//        reference = FirebaseStorage.getInstance().getReference();
//        mdatabase = FirebaseDatabase.getInstance().getReference().child("Incident");
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new);
//
//        typeSpinner = (Spinner)findViewById(R.id.inciType);
//        adapter = ArrayAdapter.createFromResource(this,R.array.InciType,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        typeSpinner.setAdapter(adapter);
//
//        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//               // Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"selected" ,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//
//
//    }
//
//    private void saveNewIncident(){
//
//        String id = IDInci.getText().toString().trim();
//        String repoName = reporterName.getText().toString().trim();
//       // String type = typeSpinner.().toString().trim();
//        String resName = responsePerson.getText().toString().trim();
//        String note = Note.getText().toString().trim();
//        String date =savedate.getText().toString().trim();
//        String time = savetime.getText().toString().trim();
//
//        saveNewIncident saveNewIncident = new saveNewIncident(id,repoName,resName,note,date,time);
//
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//        databaseReference.child(user.getUid()).setValue(saveNewIncident);
//
//        Toast.makeText(this, "Information Saves...",Toast.LENGTH_LONG).show();
//
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//        if(v == addNewInci){
//            saveNewIncident();
//        }
//
//    }


    private static final String TAG = AddNew.class.getSimpleName();


    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private ImageButton addNewInci;

    private ImageButton homeBTN;

    private EditText IDInci;
    private EditText reporterName;
    private EditText typeInci;
    private EditText responsePerson;
    private EditText Note;
    private EditText savedate;
    private EditText savetime;
    //defining a database reference
    private DatabaseReference databaseReference;

//    //our new views
//    private EditText editTextName, editTextAddress;
//    private ImageButton buttonSave;

    public void onclickHome(){
        homeBTN = (ImageButton) findViewById(R.id.homeAdd);
        homeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class );
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, SingIN.class));
        }

        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //getting the views from xml resource
        IDInci= (EditText) findViewById(R.id.ID);
        reporterName = (EditText) findViewById(R.id.reporterName);
        responsePerson= (EditText) findViewById(R.id.responser);
        Note= (EditText) findViewById(R.id.note);
        savedate= (EditText) findViewById(R.id.date);
        savetime= (EditText) findViewById(R.id.time);
        typeInci = (EditText) findViewById(R.id.type);


        addNewInci = (ImageButton) findViewById(R.id.addNewIncident);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        Log.d(TAG,user.getEmail());

//        //initializing views
//        IDInci = (TextView) findViewById(R.id.textViewUserEmail);
//        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
     //   textViewUserEmail.setText("Welcome " + user.getEmail());

        //adding listener to button
        addNewInci.setOnClickListener(this);
       // buttonSave.setOnClickListener(this);

        onclickHome();
    }


    private void saveUserInformation() {
        //Getting values from database
        String id = IDInci.getText().toString().trim();
        String repoter = reporterName.getText().toString().trim();
        String type = typeInci.getText().toString().trim();
        String respons = responsePerson.getText().toString().trim();
        String note = Note.getText().toString().trim();
        String dates = savedate.getText().toString().trim();
        String times = savetime.getText().toString().trim();
        // String incidentType = type.getSelectedItem().toString();

      /*  //creating a userinformation object
        saveNewIncident saveNewIncident = new saveNewIncident(id,repoter,respons,note,dates,times);

        //getting the current logged in user
        FirebaseUser user = firebaseAuth.getCurrentUser();


       // databaseReference = FirebaseDatabase.getInstance()
        //databaseReference.child(user.getUid()).setValue(saveNewIncident);*/

        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(repoter) && !TextUtils.isEmpty(respons) && !TextUtils.isEmpty(note) && !TextUtils.isEmpty(dates)
                && !TextUtils.isEmpty(times)) {




        Incident saveNewIncident = new Incident(id, repoter,type , respons, note, dates, times);
            databaseReference.child("user").child(firebaseAuth.getCurrentUser().getUid()).push().setValue(saveNewIncident);


        //displaying a success toast
        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();
    }
    else {
            Toast.makeText(this, "Please Fill the fields", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        /*//if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }*/

        if(view == addNewInci){
            saveUserInformation();
        }

    }

}
