package com.example.bill02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Adddata extends AppCompatActivity {

    public Button scan,add,view,deletedata;
    public static TextView codeid;
    public EditText name,price;
    public static DatabaseHelper mydatabse;
    public String prodname,prodid;
    public int prodno,prodprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);
        scan =(Button)findViewById(R.id.scan);
        mydatabse =new DatabaseHelper(this);
        add=(Button)findViewById(R.id.add);
        view=(Button)findViewById(R.id.view);
        codeid=(TextView)findViewById(R.id.codeid);
        name=(EditText)findViewById(R.id.name);
        price=(EditText)findViewById(R.id.price);
        deletedata =(Button) findViewById( R.id.delete );
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Adddata.this, ScanActivity.class);
                startActivity(intent);
            }
        });
        AddData();
        viewData();
        delete();

    }
    public void AddData(){

        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prodname = name.getText().toString();
                prodid = codeid.getText().toString();
                prodprice = Integer.parseInt(price.getText().toString());

                //Current Date and Time

                boolean isInserted = mydatabse.instertData( prodid,prodname,prodprice);

                if(isInserted == true){
                    Toast.makeText( Adddata.this, "Data is inserted", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( Adddata.this, "Data is not inserted", Toast.LENGTH_SHORT ).show();
                name.setText("");
                codeid.setText("");
                price.setText("");
            }
        } );
    }

    //For viewing data in database
    public void viewData(){

        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydatabse.getData();

                if (res.getCount() == 0){
                    showMessage("Error", "Data not found!");
                }

                else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append( "ID: " + res.getString( 0 ) + "\n" );
                        buffer.append( "Barcode Id: " + res.getString( 1 ) + "\n" );
                        buffer.append( "Name: " + res.getString( 2 ) + "\n" );
                        buffer.append( "Price: " + res.getString( 3 ) + "\n" );

                    }

                    showMessage( "Data", buffer.toString() );

                }
            }
        } );
    }

    //For updating existing data in database


    //For deleting data in the database
    public void delete(){
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodid = codeid.getText().toString();
                int isdeleted = mydatabse.deleteData(prodid);

                if(isdeleted >0){
                    Toast.makeText( Adddata.this, "Data is deleted", Toast.LENGTH_SHORT ).show();
                }
                else
                    Toast.makeText( Adddata.this, "Data is not deleted", Toast.LENGTH_SHORT ).show();
                codeid.setText("");
            }
        });

    }


    //Method for creating AlertDialog box
    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle( title );
        builder.setMessage( message );
        builder.show();
    }

}
