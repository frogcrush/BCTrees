package com.tylorstech.bctrees;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<TreeEntry> trees = new ArrayList<TreeEntry>();

    public TreeAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return trees.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView)view;
        }

        imageView.setImageResource(trees.get(i).IconResource); //TODO: set image
        return imageView;
    }
}
