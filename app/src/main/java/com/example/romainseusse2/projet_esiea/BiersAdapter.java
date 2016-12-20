package com.example.romainseusse2.projet_esiea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by romain seusse 2 on 15/11/2016.
 */
public class BiersAdapter extends RecyclerView.Adapter<BierHolder> {

    private JSONArray biers;
    private Context ctx;

    public BiersAdapter(JSONArray biers, Context ctx){
        this.biers = biers;
        this.ctx = ctx;
    }

    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.rv_biere_element,null);
        return new BierHolder(v);

    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        Bitmap bm = null;
        try {
            JSONObject b = biers.getJSONObject(position);
            Picasso.with(ctx).load(b.getString("thumbnailUrl")).into(holder.bier_imageview);
            Picasso.with(ctx).load(b.getString("mainImageUrl")).into(holder.bier_imageview2);

        } catch (JSONException e) {
            Log.i("JSONObjetTag", "JSONObject Unfoundable");
        }
    }

    public void update(JSONArray b){

        biers = b;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return biers.length();
    }
}
