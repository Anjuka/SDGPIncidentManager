package com.example.anjukakoralage.sdgpresit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class viewAll extends AppCompatActivity {

    ListView view;
    CustomAdapterViewAll adapter;
    ArrayList<Incident> list = new ArrayList<Incident>();
    FirebaseDatabase database;
    FirebaseAuth fireAuth;
    FirebaseUser user;
    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        view = (ListView)findViewById(R.id.listViewAl);

        fireAuth = FirebaseAuth.getInstance();

        final String userID = fireAuth.getCurrentUser().getUid();

        System.out.println("\n\n\n\nUser : "+userID+"\n\n\n\n");

        data = FirebaseDatabase.getInstance().getReference();



        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,Object> map = (HashMap<String, Object>) dataSnapshot.child("user").child(userID).getValue();

                    System.out.println("heeeeeeeeeeere");

                String uid = "";

                list = new ArrayList<>();

                for(DataSnapshot snap : dataSnapshot.child("user").child(userID).getChildren()){
                    Log.e("User key", snap.getKey());
                    Log.e("User ref", snap.getRef().toString());
                    Log.e("User val", snap.getValue().toString());
                    Incident inc = new Incident();
                    inc = snap.getValue(Incident.class);
                    inc.uid = snap.getKey();
                    list.add(inc);
                    //uid = snap.child("user").child(userID);
                }

                    /*for(Object t : map.values()){

                        String s=new Gson().toJson(t);
                        Log.e("ggggggggg","fff"+s);
                        Incident view = new Gson().fromJson(s,Incident.class);
                        Log.e("ggggggggg","fff"+s+" : "+uid);
                        System.out.println("value of s "+s);

                    }*/
                adapter = new CustomAdapterViewAll(list,getApplicationContext());
                view.setAdapter(adapter);
                view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Incident inc = list.get(position);
                        Intent intent = new Intent(viewAll.this,ViewIncident.class);
                        intent.putExtra("view" ,inc);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        data.addValueEventListener(postListener);

    }
}
