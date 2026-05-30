package ru.mirea.stulovad.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import ru.mirea.stulovad.mireaproject.databinding.ActivityLogInBinding;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityLogInBinding binding;
    // START declare_auth
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(binding.emailte.getText().toString(), binding.passwordte.getText().toString());
            }
        });

        binding.singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(binding.emailte.getText().toString(), binding.passwordte.getText().toString());
            }
        });
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LogInActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure",
                                    task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LogInActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
