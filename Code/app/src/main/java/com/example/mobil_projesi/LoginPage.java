package com.example.mobil_projesi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    EditText username, password;
    Button signInBtn;
    DatabaseHelper _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.usernameInLoginXML);
        password = (EditText) findViewById(R.id.passwordInLoginXML);
        signInBtn = (Button) findViewById(R.id.btnSignin);
        _db = new DatabaseHelper(this);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String usernameFromUser = username.getText().toString();
                String passwordFromUser = password.getText().toString();

                boolean check = _db.checkUsernamePassword(usernameFromUser, passwordFromUser);
                if ( passwordFromUser.equals("") || usernameFromUser.equals("")) {
                    Toast.makeText(LoginPage.this, "Please Fill All Fields" , Toast.LENGTH_LONG).show();
                }
                else{

                    if(check == true){
                        Toast.makeText(LoginPage.this, "Login Success" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(LoginPage.this, "Login Failed" , Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }



}