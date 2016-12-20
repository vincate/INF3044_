package com.example.romainseusse2.projet_esiea;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SecondaryActivity extends AppCompatActivity implements OnListener{

    public static String BEERS_UPDATE = "B";
    private static BiersAdapter ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        IntentFilter filter = new IntentFilter(BEERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(this), filter);


        ba = new BiersAdapter(getBiersFromFile(), this);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_biere);
        if (rv != null) {
            rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
            rv.setAdapter(ba);
        }

        Button btn = (Button)findViewById(R.id.btn_dl_beer);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetBiersServices.startActionBeer(getApplicationContext());
                }
            });
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public JSONArray getBiersFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres3.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        }catch (IOException e){
            Log.i("File tag", "Fichier introuvable");
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage(getString(R.string.dialog))
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return new JSONArray();
        }
        catch (JSONException e){
            Log.i("JSON tag", "JSON corrompu");
            return new JSONArray();
        }
    }


    @Override
    public void onFinish() {
        ba.update(getBiersFromFile());
    }

}
