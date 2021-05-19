package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SellerPage extends AppCompatActivity {

    TextView name ;
    Button mapBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);
        name = (TextView) findViewById(R.id.SellerNameInSellerPage);
        Intent intent = getIntent();
        String value = intent.getStringExtra("Name");
        name.setText(value);
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
        mapBtn = (Button) findViewById(R.id.getMapBtn);

        Fragment fragment = new MapFragment();
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameMap, fragment)
                        .commit();



            }
        });
    }
}