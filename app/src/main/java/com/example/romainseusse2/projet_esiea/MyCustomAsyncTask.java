package com.example.romainseusse2.projet_esiea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by romain seusse 2 on 20/12/2016.
 */
public class MyCustomAsyncTask extends AsyncTask<Void,Void,Void> {
    private Context context;

    public MyCustomAsyncTask(Context context){
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ssbwiki.com/Super_Smash_Bros._4"));
        context.startActivity(i);
        ((Activity)context).finish();
    }
}
