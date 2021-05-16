package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    EditText FirstName, LastName, Email, Address, Password,RePassword,Username;
    RadioGroup radioGroup;
    RadioButton radioBtn;

    Button SignupBtn;
    DatabaseHelper _db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        FirstName = (EditText) findViewById(R.id.firstNameEdtTxt);
        LastName = (EditText) findViewById(R.id.lastNameEdtTxt);
        Email  = (EditText) findViewById(R.id.emailEdtTxt);
        Address = (EditText) findViewById(R.id.addressEdtTxt);
        Password = (EditText) findViewById(R.id.passworEdtTxt);
        RePassword = (EditText) findViewById(R.id.retypePassword);
        Username = (EditText) findViewById(R.id.usernameInSignUpXML);
        radioGroup = (RadioGroup) findViewById(R.id.rdGroupSignupXML);

        SignupBtn = (Button) findViewById(R.id.SignUpBtn);

        _db = new DatabaseHelper(this);


        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioBtn = findViewById(radioId);
                String[] Credentials = {Username.getText().toString() , FirstName.getText().toString(), LastName.getText().toString(), Email.getText().toString()
                        , Address.getText().toString(), Password.getText().toString() , RePassword.getText().toString(), radioBtn.getText().toString()};

                if (Credentials[0].equals("") || Credentials[1].equals("") || Credentials[2].equals("") || Credentials[3].equals("") || Credentials[4].equals("")
                        || Credentials[5].equals("") || Credentials[6].equals("") ) {
                    Toast.makeText(RegisterPage.this, "Please Fill All Fields", Toast.LENGTH_LONG).show();
                } else {
                    boolean checkUsernameExist = _db.checkUsername(Credentials[0]);
                    if(checkUsernameExist == true){
                        Toast.makeText(RegisterPage.this , "The Username Already Exists" , Toast.LENGTH_LONG).show();
                    }
                    else{
                        _db.addUser(Credentials[0] , Credentials[1] ,Credentials[2] ,Credentials[5],Credentials[3],Credentials[4],Credentials[7], 0);
                        Toast.makeText(RegisterPage.this, "Register Success" ,Toast.LENGTH_LONG).show();
                    }
                 }
          }
        });

    }
}