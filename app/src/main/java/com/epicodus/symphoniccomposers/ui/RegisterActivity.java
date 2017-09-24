package com.epicodus.symphoniccomposers.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.symphoniccomposers.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.reg_display_name) TextInputLayout mDisplayName;
    @Bind(R.id.reg_email) TextInputLayout mEmail;
    @Bind(R.id.reg_password) TextInputLayout mPassword;
    @Bind(R.id.reg_create_btn) Button mCreateBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);

        mCreateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == mCreateBtn) {

            String display_name = mDisplayName.getEditText().getText().toString();
            String email = mEmail.getEditText().getText().toString();
            String password = mPassword.getEditText().getText().toString();

            register_user(display_name, email, password);
        }
    }

    private void register_user(String display_name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Your registration failed.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
