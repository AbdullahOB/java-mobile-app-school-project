package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BuyerPage extends AppCompatActivity {
    TextView name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_page);
        name = (TextView) findViewById(R.id.BuyerNameInBuyerPage);
        Intent intent = getIntent();
        String value = intent.getStringExtra("Name");
        name.setText(value);
    }
}