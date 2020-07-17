package com.e.patientportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextInputEditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseDatabase = FirebaseDatabase.getInstance();
        email = findViewById(R.id.editPatientID);
        pass = findViewById(R.id.editPassword);
        databaseReference = firebaseDatabase.getReference("users");
    }
    public void onClickLogin(View view){
        if (TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(pass.getText())){
            Toast.makeText(getApplicationContext(),"FIELDS CANNOT BE EMPTY",Toast.LENGTH_LONG).show();
        }
        else{
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(email.getText().toString()).exists()){
                        String password = (String) dataSnapshot.child(email.getText().toString()).child("password").getValue();
                        boolean first = (boolean) dataSnapshot.child(email.getText().toString()).child("first").getValue();
                        if (password.equals(pass.getText().toString())){
                            SaveSharedPreference.setUserName(getApplicationContext(),email.getText().toString());
                            if (first){
                                Intent intent = new Intent(LoginActivity.this,SetProfileActivity.class);
                                intent.putExtra("pass",password);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this,DashActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"PASSWORD DOES NOT MATCH",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"PATIENT DOESNT EXISTS",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}