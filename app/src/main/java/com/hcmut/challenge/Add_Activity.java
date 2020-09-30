package com.hcmut.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Add_Activity extends AppCompatActivity {

    EditText etTitle, etDesc, etDate;
    Button btnCreate, btnCancel;
    DatabaseReference reference;
    Integer randomInt = new Random().nextInt();
    String keydoes = Integer.toString(randomInt);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle=findViewById(R.id.etTitle);
        etDesc=findViewById(R.id.etDesc);
        etDate=findViewById(R.id.etDate);
        btnCancel=findViewById(R.id.btnCancel);
        btnCreate=findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference =FirebaseDatabase.getInstance().getReference().child("DoesApp").child("Does"+randomInt);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("titledoes").setValue(etTitle.getText().toString().trim());
                        snapshot.getRef().child("desdoes").setValue(etDesc.getText().toString().trim());
                        snapshot.getRef().child("datedoes").setValue(etDate.getText().toString().trim());
                        snapshot.getRef().child("keydoes").setValue(keydoes);
                        //Toast.makeText(Add_Activity.this,"Successfully added! + key: "+keydoes,Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(Add_Activity.this, MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Add_Activity.this,"Failed to add new task",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Add_Activity.this,"Failed to add new task",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
