package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPage extends AppCompatActivity {

    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    public void loginBtnClicked(View view){
        @SuppressLint("WrongViewCast") TextInputEditText usernameUser =  findViewById(R.id.usernameInLoginXML);
        @SuppressLint("WrongViewCast") TextInputEditText passwordUser =  findViewById(R.id.passwordInLoginXML);

        try {
            ConnectionHelper connHelp = new ConnectionHelper();
            connect = ConnectionHelper.connectionClass();
            if(connect != null){
                String query = "Select * from KullaniciTable";
                Statement st =  connect.createStatement();
                ResultSet rs = st.executeQuery(query);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}