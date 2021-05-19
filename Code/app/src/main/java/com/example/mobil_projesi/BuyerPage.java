package com.example.mobil_projesi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyerPage extends AppCompatActivity {
    TextView name ;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText PopUp_BalanceReq;
    private Button PopUp_RequestBtn, PopUp_cancelBtn;
    private DatabaseHelper _db;
    private Button AddBalanceBtn;
    private int UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_page);
        name = (TextView) findViewById(R.id.BuyerNameInBuyerPage);
        Intent intent = getIntent();
        String value = intent.getStringExtra("Name");
        UserId = intent.getIntExtra("Uid" , 0);
        name.setText(value);
        _db = new DatabaseHelper(this);

        AddBalanceBtn = (Button) findViewById(R.id.AddBalanceBuyer);

        AddBalanceBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createNewPopUp();
            }
        });
    }

    public void createNewPopUp(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View PopUp = getLayoutInflater().inflate(R.layout.popup, null);
        PopUp_BalanceReq = (EditText) PopUp.findViewById(R.id.enterBalancePopup);
        PopUp_RequestBtn = (Button) PopUp.findViewById(R.id.reqBtnPopup);
        PopUp_cancelBtn = (Button) PopUp.findViewById(R.id.cancetBtnPopup);
        dialogBuilder.setView(PopUp);
        dialog = dialogBuilder.create();
        dialog.show();
        createNotification();

        NotificationCompat.Builder notify = new NotificationCompat.Builder(this, "BildirimA")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("Bildirim ")
                .setContentText("Bildirim Lorem Ipsum Dolor Sit Amit")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        PopUp_RequestBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Float val = Float.parseFloat(PopUp_BalanceReq.getText().toString());
                    _db.AddBuyerRequest(val, UserId );
                    notificationManager.notify(100,notify.build());
            }
        });

        PopUp_cancelBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void createNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           CharSequence name = "Bildirimler";
           String desc = "Channel Description";
           int importance = NotificationManager.IMPORTANCE_DEFAULT;
           NotificationChannel channel = new NotificationChannel("BildirimA", name, importance);
           channel.setDescription(desc);
           NotificationManager notificationManager = getSystemService(NotificationManager.class);
           notificationManager.createNotificationChannel(channel);

      }
  }
}