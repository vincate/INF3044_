package com.example.romainseusse2.projet_esiea;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by romain seusse 2 on 15/11/2016.
 */
public class BierUpdate extends BroadcastReceiver {
    OnListener listener;
    public BierUpdate(OnListener l){
        listener = l;
    }

    @Override
    public void onReceive(Context ctx, Intent i){
        Log.d("biertag","ca dl poto tkt");
        Toast.makeText(ctx,"List Updated",Toast.LENGTH_LONG).show();
        listener.onFinish();
    }
}
