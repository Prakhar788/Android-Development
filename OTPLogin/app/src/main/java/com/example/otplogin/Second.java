package com.example.otplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Second extends AppCompatActivity {
    Button b1;
    EditText e1;
    FirebaseAuth firebaseAuth;
    String otp,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        b1=(Button) findViewById(R.id.button2);
        e1=(EditText) findViewById(R.id.editText2);
        firebaseAuth=FirebaseAuth.getInstance();
        phone=getIntent().getStringExtra("mobile").toString();
        genotp();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty())
                {
                    e1.setError("Enter your OTP");
                }
                else if(e1.getText().toString().length()!=6)
                {
                    e1.setError("Enter your OTP correctly");
                }
                else
                {
                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otp,e1.getText().toString());
                    siginwithPhoneAuthCredential(credential);
                }
            }
        });
    }
    private void genotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                30,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otp=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        siginwithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(Second.this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    private void siginwithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Second.this, "Database Updated", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Second.this,Third.class);
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(Second.this, "Database not updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}