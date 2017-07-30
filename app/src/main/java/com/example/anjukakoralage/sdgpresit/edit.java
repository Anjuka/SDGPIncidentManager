package com.example.anjukakoralage.sdgpresit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class edit extends AppCompatActivity {


    private static final String TAG = AddNew.class.getSimpleName();

    //view objects
    private ImageButton addNewInci;

    private EditText IDInci;
    private EditText reporterName;
    private EditText typeInci;
    private EditText responsePerson;
    private EditText Note;
    private EditText savedate;
    private EditText savetime;
    //defining a database reference
    DatabaseReference data;
    FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        fireAuth = FirebaseAuth.getInstance();

        final String userID = fireAuth.getCurrentUser().getUid();

        System.out.println("\n\n\n\nUser : "+userID+"\n\n\n\n");

        data = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        final Incident inc =  (Incident)intent.getSerializableExtra("inc");

        //getting the views from xml resource
        IDInci= (EditText) findViewById(R.id.ID);
        reporterName = (EditText) findViewById(R.id.reporterName);
        responsePerson= (EditText) findViewById(R.id.responser);
        Note= (EditText) findViewById(R.id.note);
        savedate= (EditText) findViewById(R.id.date);
        savetime= (EditText) findViewById(R.id.time);
        typeInci = (EditText) findViewById(R.id.type);

        IDInci.setText(inc.ID);
        reporterName.setText(inc.ReporterName);
        responsePerson.setText(inc.ResponsPerson);
        Note.setText(inc.Note);
        savedate.setText(inc.Date);
        savetime.setText(inc.Time);
        typeInci.setText(inc.IncidentType);

        addNewInci = (ImageButton) findViewById(R.id.editBTN);

        addNewInci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.child("user").child(userID).child(inc.uid).child("ID").setValue(IDInci.getText().toString());
                data.child("user").child(userID).child(inc.uid).child("Date").setValue(savedate.getText().toString());
                data.child("user").child(userID).child(inc.uid).child("IncidentType").setValue(typeInci.getText().toString());
                data.child("user").child(userID).child(inc.uid).child("Note").setValue(Note.getText().toString());
                data.child("user").child(userID).child(inc.uid).child("ReporterName").setValue(reporterName.getText().toString());
                data.child("user").child(userID).child(inc.uid).child("ResponsPerson").setValue(responsePerson.getText().toString());
                data.child("user").child(userID).child(inc.uid).child("Time").setValue(savetime.getText().toString());
                finish();
            }
        });

    }

}

