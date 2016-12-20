package com.example.romainseusse2.projet_esiea;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

/**
 * Created by romain seusse 2 on 15/11/2016.
 */
public class BierHolder extends RecyclerView.ViewHolder{

    private Context ctx;
    public ImageView bier_imageview2;
    public ImageButton bier_imageview;

    public BierHolder(View itemView) {
        super(itemView);
        ctx = itemView.getContext();
        bier_imageview2 =  (ImageView)itemView.findViewById(R.id.iv2_biere_element_name);
        bier_imageview =  (ImageButton)itemView.findViewById(R.id.iv_biere_element_name);

        bier_imageview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(bier_imageview2.getVisibility() == View.VISIBLE) {
                    bier_imageview2.setVisibility(View.GONE);
                }else{
                    bier_imageview2.setVisibility(View.VISIBLE);
                }

            }

        });
    }

}
