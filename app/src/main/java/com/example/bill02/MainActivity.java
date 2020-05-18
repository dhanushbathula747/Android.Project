package com.example.bill02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         e1=(EditText)findViewById(R.id.edname);
         e2=(EditText)findViewById(R.id.password);
        Button button=(Button)findViewById(R.id.bt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = e1.getText().toString();
                String b = e2.getText().toString();
                if(a.equals("cashier") && b.equals("simple")) {
                    Intent intent = new Intent(MainActivity.this, second.class);
                    startActivity(intent);
                }
            }
        });
    }
}
