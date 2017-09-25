package com.epicodus.symphoniccomposers.ui;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = RegisterActivity.class.getSimpleName();

    @Bind(R.id.reg_display_name) TextInputLayout mDisplayName;
    @Bind(R.id.login_email) TextInputLayout mEmail;
    @Bind(R.id.login_password) TextInputLayout mPassword;
    @Bind(R.id.reg_confirm_password) TextInputLayout mConfirmPassword;
    @Bind(R.id.reg_create_btn) Button mCreateBtn;

    private ProgressDialog mAuthProgressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);

        mCreateBtn.setOnClickListener(this);

        createAuthProgressDialog();
        createAuthStateListener();

    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
              final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {

        if(v == mCreateBtn) {

            register_user();
        }
    }

    private void register_user() {
        mName = mDisplayName.getEditText().getText().toString().trim();

        final String display_name = mDisplayName.getEditText().getText().toString().trim();
        final String email = mEmail.getEditText().getText().toString().trim();
        String password = mPassword.getEditText().getText().toString().trim();
        String confirmPassword = mConfirmPassword.getEditText().getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(mName);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();

                if(task.isSuccessful()){
//                    createFirebaseUserProfile(task.getResult().getUser());
                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Your registration failed.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mDisplayName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                            Log.d(TAG, user.getDisplayName());
                        }
                    }

                });
    }
}
