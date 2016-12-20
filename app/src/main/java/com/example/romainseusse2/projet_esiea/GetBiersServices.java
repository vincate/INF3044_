package com.example.romainseusse2.projet_esiea;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetBiersServices extends IntentService {

    public GetBiersServices() {
        super("GetBiersServices");
    }

    // TODO: Customize helper method
    public static void startActionBeer(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        handleActionBeer();
    }
    private void handleActionBeer() {
        Log.d("threadtag","Thread service name:" + Thread.currentThread().getName());
        URL url = null;
        try{
            url = new URL("http://api.kuroganehammer.com/api/Characters");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(HttpURLConnection.HTTP_OK == conn.getResponseCode())
            {
                copyInputSteamToFile(conn.getInputStream(), new File(getCacheDir(),"bieres3.json"));
                Log.d("dltag","Bieres json downloaded!");
            }
        }catch (MalformedURLException e){
            Log.i("urltag","Wrong URL");
        }catch (IOException e){
            Log.i("IOtag","Wrong IO");
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(SecondaryActivity.BEERS_UPDATE));
    }

    private void copyInputSteamToFile(InputStream in , File file){
        try{
            OutputStream out = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int len;
            while((len = in.read(buff)) > 0){
                out.write(buff,0,len);
            }
            out.close(); in.close();

        }catch (Exception e)
        {
            Log.i("filetag","File not found!!!");

        }
    }
}
