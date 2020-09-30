package com.hcmut.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_Activity extends AppCompatActivity {

    EditText etTitle, etDesc, etDate;
    Button btnChange, btnDelete;
    DatabaseReference reference;
    String doesnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etDate = findViewById(R.id.etDate);
        btnChange = findViewById(R.id.btnChange);
        btnDelete = findViewById(R.id.btnDelete);

        etTitle.setText(getIntent().getStringExtra("titledoes"));
        etDesc.setText(getIntent().getStringExtra("desdoes"));
        etDate.setText(getIntent().getStringExtra("datedoes"));
        doesnum = getIntent().getStringExtra("keydoes");
        reference = FirebaseDatabase.getInstance().getReference().child("DoesApp").child("Does"+ doesnum);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final String Desc = etDesc.getText().toString();
                        final String Title = etTitle.getText().toString();
                        final String Date =etDate.getText().toString();
                        snapshot.getRef().child("titledoes").setValue(Title);
                        snapshot.getRef().child("desdoes").setValue(Desc);
                        snapshot.getRef().child("datedoes").setValue(Date);
                        snapshot.getRef().child("keydoes").setValue(doesnum);
                        //Toast.makeText(Edit_Activity.this,"Successfully edited! + key: "+doesnum,Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(Edit_Activity.this,MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// use getParent() to access parent node
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                         if (task.isComplete()){
                             Intent a = new Intent(Edit_Activity.this,MainActivity.class);
                             startActivity(a);
                         }
                         else{
                             Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                         }
                    }
                });
            }
        });
    }
}