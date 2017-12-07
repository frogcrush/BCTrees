package com.tylorstech.bctrees;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainListActivity extends AppCompatActivity {
    public static ArrayList<TreeEntry> Entries; //bad practice but w/e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        final TreeAdapter myAdapter = new TreeAdapter(this);
        GridView gridview = (GridView) findViewById(R.id.gridview);

        if (Entries == null){
            Entries = JsonLoader.GetTrees(this);
        }

        myAdapter.trees = Entries;

        gridview.setAdapter(myAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(MainListActivity.this, DetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }


}
