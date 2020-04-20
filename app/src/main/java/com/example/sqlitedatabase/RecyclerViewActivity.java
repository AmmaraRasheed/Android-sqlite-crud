package com.example.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Data> notesList = new ArrayList<>();
    private RecyclerView recyclerView;
    DatabaseHelper db;
   public static NotesAdapter mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerview);
        //where you use object of databasehelper (db)
        //you have to initialize it.
        db = new DatabaseHelper(this);
        //get all data from database and store in list (noteslist)
        //then pass to recyclerview.

        notesList.addAll(db.getAllDataFromDb());
        mAdapter1 = new NotesAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter1);


    }
}
