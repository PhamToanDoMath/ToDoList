package com.hcmut.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    DatabaseReference reference;
    TextView titlePage, titleDesc, titleEnd;
    Button btnAdd;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;
    ArrayList<ToDo> toDo_List;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titlePage =findViewById(R.id.titlePage);
        titleDesc = findViewById(R.id.titleDesc);
        titleEnd = findViewById(R.id.titleEnd);
        btnAdd = findViewById(R.id.btnAdd);

        toDo_List = new ArrayList<>();
        recyclerView = findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.hcmut.challenge.Add_Activity.class);
                startActivity(intent);
            }
        });

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("DoesApp");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ToDo p= dataSnapshot.getValue(ToDo.class);
                    toDo_List.add(p);
                }
                //set up adapter from recycler view
                myAdapter = new ToDoAdapter(MainActivity.this,toDo_List);
                recyclerView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to get the information",Toast.LENGTH_SHORT).show();
            }
        });


    }

    //@Override
    //public void onItemClicked(int index) {
    //}
}
