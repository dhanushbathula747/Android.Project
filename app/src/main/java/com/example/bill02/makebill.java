package com.example.bill02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.bill02.Adddata.mydatabse;

public class makebill extends AppCompatActivity {
    public static TextView temp;
    public static StringBuffer buffer;
    DatabaseHelper databaseHelper;
    public static int sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makebill);
        final Button scan =(Button)findViewById(R.id.scan);
        Button view=(Button)findViewById(R.id.view1);
        Button senddata=(Button)findViewById(R.id.send);
        Button add=(Button)findViewById(R.id.add);
        sum=0;
        DatabaseHelper mydatabse1 = mydatabse;
        buffer = new StringBuffer();
        temp=(TextView)findViewById(R.id.temp);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(makebill.this,scanactivity2.class);
                startActivity(intent1);
                }
        });
        senddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showMessage("Data", "start doing sms ");
                Intent intent=new Intent(makebill.this,sendsms.class);
                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Your Bill",buffer.toString());
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=temp.getText().toString();
                Cursor res = mydatabse.searchdata(s);
                if (res.getCount() == 0) {
                    showMessage("Error", "Data not found!");
                }else {
                    while (res.moveToNext()) {
                        buffer.append("Name: " + res.getString(2) + "\n");
                        buffer.append("Price: " + res.getString(3) + "\n");
                        sum=sum+Integer.parseInt(res.getString(3));
                    }
                }
            }
        });
    }

    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle( title );
        builder.setMessage( message +"\n"+"Total sum"+sum );
        builder.show();
    }
}
