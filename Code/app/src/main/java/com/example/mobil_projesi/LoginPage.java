package com.example.mobil_projesi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
                            String userType = _db.getUserType(usernameFromUser);
                            if(userType.equals("Buyer")){
                                Toast.makeText(LoginPage.this, "Login Success Your Role Is : "+userType , Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginPage.this, BuyerPage.class);
                                intent.putExtra("Name" , usernameFromUser);
                                LoginPage.this.startActivity(intent);
                            }
                            else if(userType.equals("Seller")){
                                Toast.makeText(LoginPage.this, "Login Success Your Role Is : "+userType , Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginPage.this, SellerPage.class);
                                intent.putExtra("Name" , usernameFromUser);
                                LoginPage.this.startActivity(intent);
                            }
                            else if(userType.equals("Admin")){
                                Toast.makeText(LoginPage.this, "Login Success Your Role Is : "+userType , Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginPage.this, AdminPage.class);
                                LoginPage.this.startActivity(intent);
                            }

                    }
                    else{
                        Toast.makeText(LoginPage.this, "Login Failed Please Check your role, username and password" , Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }



}