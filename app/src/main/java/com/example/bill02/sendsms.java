package com.example.bill02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendsms extends AppCompatActivity {
    private EditText name;
    private EditText number;
    private Button send;
    public StringBuffer s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);
        name = (EditText)findViewById(R.id.name);
        number = (EditText)findViewById(R.id.number);
        send = (Button)findViewById(R.id.send);
        s=makebill.buffer;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(number.getText().toString(),null,name.getText().toString()+"\n"+s.toString() +"\nTotal bill is "+makebill.sum,null,null);
                    Toast.makeText(sendsms.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(sendsms.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
