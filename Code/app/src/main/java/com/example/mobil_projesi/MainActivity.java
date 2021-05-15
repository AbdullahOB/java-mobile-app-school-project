package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginPageBtn(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

    }
    public void RegisterPageBtn(View view){
        Intent intent = new Intent(this,RegisterPage.class);
        startActivity(intent);
    }
}