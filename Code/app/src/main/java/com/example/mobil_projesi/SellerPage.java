package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SellerPage extends AppCompatActivity {

    TextView name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);
        name = (TextView) findViewById(R.id.SellerNameInSellerPage);
        Intent intent = getIntent();
        String value = intent.getStringExtra("Name");
        name.setText(value);
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }
}