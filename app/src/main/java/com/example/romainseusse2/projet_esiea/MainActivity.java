 package com.example.romainseusse2.projet_esiea;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


 public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
     public boolean onCreateOptionsMenu(Menu menu) {

         getMenuInflater().inflate(R.menu.menu_main, menu);

         return true;

     }

     public boolean onOptionsItemSelected(MenuItem item) {

         switch (item.getItemId()) {

             /*case R.id.btn_toast:
                 Toast.makeText(getApplicationContext(),getString(R.string.toast),Toast.LENGTH_LONG).show();

                 return true;

             case R.id.btn_notif:
                createNotification();

                 return true;

             case R.id.btn_dialog:
                 new AlertDialog.Builder(this)
                         .setTitle("Dialog Box Title")
                         .setMessage(getString(R.string.dialog))
                         .setIcon(android.R.drawable.ic_dialog_alert)
                         .show();

                 return true;*/

             case R.id.btn_beer:
                 //GetBiersServices.startActionBeer(this);
                 new MyCustomAsyncTask(this).execute();
                 createNotification();
                 return true;
         }

         return super.onOptionsItemSelected(item);

     }

     private void createNotification(){

         PendingIntent contentIntent = PendingIntent.getActivity(this,0 , new Intent(this, MainActivity.class),0);

         Notification n = new Notification.Builder(this)
                .setSmallIcon(R.drawable.smash_ball)
                 .setContentIntent(contentIntent)
                 .setAutoCancel(true)
                 .setContentTitle("SSB4 Running...")
                 .setContentText(getString(R.string.notif))
                 .build()
                 ;

         NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
         nm.notify(0, n);


     }

 }
