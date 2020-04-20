package com.example.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        //getting id
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
    }

    public void Save(View view){
        String saveEmail=email.getText().toString();
        String savePass=pass.getText().toString();
        db.insertData(saveEmail,savePass);
        Intent intent=new Intent(MainActivity.this,RecyclerViewActivity.class);
        startActivity(intent);

    }
}
