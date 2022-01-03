package com.example.miniprojet;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email , password;
    Button login;
    String emailPatern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog pd;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignUp);

        email=findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        pd = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }


            private  void loginUser () {
                String validEmail = email.getText().toString();
                String validPassword = password.getText().toString();

                if (!validEmail.matches(emailPatern)){
                    email.setError("invald email");
                }
                else if (validPassword.isEmpty() || validPassword.length()<6) {
                    password.setError("invalid password");
                }

                else{
                    pd.setMessage("please wait until Login");
                    pd.setTitle("Login");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();
                    mAuth.signInWithEmailAndPassword(validEmail,validPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                pd.dismiss();
                                sendUser();
                                Toast.makeText(LoginActivity.this , "Login Successful" , Toast.LENGTH_LONG);
                            }

                        }
                    });

                }
            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void sendUser() {
        Intent i = new Intent(LoginActivity.this , MainActivity.class);
        i.setFlags(i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


}
