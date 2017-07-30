package com.example.anjukakoralage.sdgpresit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewIncident extends AppCompatActivity {

    TextView ID;
    TextView reportName;
    TextView type;
    TextView responser;
    TextView note;
    TextView date;
    TextView time;
    Button editBtn;
    Button delBtn;
    DatabaseReference data;
    FirebaseAuth fireAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_incident);

        fireAuth = FirebaseAuth.getInstance();

        final String userID = fireAuth.getCurrentUser().getUid();

        System.out.println("\n\n\n\nUser : "+userID+"\n\n\n\n");

        data = FirebaseDatabase.getInstance().getReference();

        ID = (TextView)findViewById(R.id.ID);
        reportName = (TextView)findViewById(R.id.reporterName);
        type = (TextView)findViewById(R.id.type);
        responser = (TextView)findViewById(R.id.responser);
        note = (TextView)findViewById(R.id.note);
        date = (TextView)findViewById(R.id.date);
        time = (TextView)findViewById(R.id.time);
        editBtn = (Button)findViewById(R.id.editBT);
        delBtn = (Button)findViewById(R.id.deleteBTN);

        final Intent intent = getIntent();
        final Incident currentIncident = (Incident) intent.getSerializableExtra("view");

        ID.setText(currentIncident.ID);
        reportName.setText(currentIncident.ReporterName);
        type.setText(currentIncident.IncidentType);
        responser.setText(currentIncident.ResponsPerson);
        note.setText(currentIncident.Note);
        date.setText(currentIncident.Date);
        time.setText(currentIncident.Time);

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.child("user").child(userID).child(currentIncident.uid).removeValue();
                finish();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(ViewIncident.this, edit.class);
                edit.putExtra("inc", currentIncident);
                startActivity(edit);
                finish();
            }
        });


    }
}
