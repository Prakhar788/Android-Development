package com.example.realtimedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Third extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        e1=(EditText) findViewById(R.id.editText3);
        e2=(EditText) findViewById(R.id.editText4);
        e3=(EditText) findViewById(R.id.editText5);
        b1=(Button) findViewById(R.id.button3);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference=firebaseDatabase.getReference("Users");
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                if (s3.length()!=10)
                {
                    Toast.makeText(Third.this, "Enter Correct Phone Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Users users=new Users(s1,s2,s3);
                    databaseReference.child(s3).setValue(users);
                    Toast.makeText(Third.this, "Database Updated", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    Intent i=new Intent(Third.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });

    }
}