package com.tylorstech.bctrees;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null){
            //hi there!
            position = getIntent().getIntExtra("position", -1);
            if (position == -1)
                return;

            //set info. I really wish Android had databinding like Xamarin Forms.
            SetItemInfo(GetItem());
        }
    }

    private TreeEntry GetItem(){
        //grab item
        if (MainListActivity.Entries == null)
            MainListActivity.Entries = JsonLoader.GetTrees(this);
        //grab item at position
        TreeEntry item = MainListActivity.Entries.get(position);
        return item;
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor e = getSharedPreferences().edit();
        e.putInt("position", position);
        e.commit();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (position != -1) return;
        int savedPos = getSharedPreferences().getInt("position", -1);
        if (savedPos != -1) {
            position = savedPos;
            SetItemInfo(GetItem());
        }
    }

    private SharedPreferences getSharedPreferences(){
        return getSharedPreferences("BCTREES", 0);
    }

    private void SetItemInfo(TreeEntry entry){
        TextView header = findViewById(R.id.headerText);
        TextView subHeader = findViewById(R.id.subHeaderText);
        TextView descrip = findViewById(R.id.descriptionText);
        ImageView headerImage = findViewById(R.id.headerImage);


        header.setText(entry.CommonName);
        subHeader.setText(entry.ScientificName);
        descrip.setText(entry.Description);
        headerImage.setImageResource(entry.FullSizeResource);
    }
}
