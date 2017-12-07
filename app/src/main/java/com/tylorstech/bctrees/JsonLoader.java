package com.tylorstech.bctrees;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonLoader {
    public static ArrayList<TreeEntry> GetTrees(Context context) {
        ArrayList<TreeEntry> returnList = new ArrayList<TreeEntry>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.optJSONArray("trees");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i); //grab the tree object

                TreeEntry t = new TreeEntry();
                t.CommonName = jo_inside.getString("common_name");
                t.Image = jo_inside.getString("image");
                t.ScientificName = jo_inside.getString("scientific_name");
                t.Description = jo_inside.getString("description");

                Resources resources = context.getResources();
                t.IconResource = resources.getIdentifier(t.Image + "_small", "drawable", context.getPackageName());
                t.FullSizeResource = resources.getIdentifier(t.Image + "_big", "drawable", context.getPackageName());
                returnList.add(t);
            }
        }catch (JSONException ex){
            Log.e("tree", "Error loading JSON list. \n" + ex.getMessage());
        }
        return returnList;
    }

    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("potato.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
