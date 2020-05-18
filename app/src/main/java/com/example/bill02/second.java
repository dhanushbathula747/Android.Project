package com.example.bill02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class second extends AppCompatActivity {
    public Button additem,makbil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        additem =(Button)findViewById(R.id.additems);
        makbil =(Button)findViewById(R.id.biller);
        makbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent());
                Intent intent=new Intent(second.this,makebill.class);
                startActivity(intent);

            }
        });

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(second.this,Adddata.class);
                startActivity(intent);
            }
        });
    }
}
