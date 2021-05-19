package com.example.mobil_projesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {
    Button UserList , MarketLogs, WaitingApproveSeller, WaitingApproveBuyer;
    DatabaseHelper _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        UserList = (Button) findViewById(R.id.userListBtn);
        MarketLogs = (Button) findViewById(R.id.marketLogsBtn);
        WaitingApproveSeller = (Button) findViewById(R.id.waitToApproveBtn);
        WaitingApproveBuyer = (Button) findViewById(R.id.BuyerBalanceWaitApprove);
        WaitingApproveBuyer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminPage.this, BuyeWaitingApproveBalance.class);
                AdminPage.this.startActivity(intent);
            }
        });


    }
}