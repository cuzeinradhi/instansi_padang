package com.main.instansipadang;

import java.util.Map;

import com.example.instansipadang.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends Activity {
	
	final Context context = this;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView btncamat = (ImageView)findViewById(R.id.imageButton2);
        btncamat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(MainActivity.this, Kantor.class);
				
				startActivity(i);
				
			}
		});
        
        ImageView btnAbout = (ImageView)findViewById(R.id.imageButton4);
		btnAbout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertAbout = new AlertDialog.Builder(context);
				
				alertAbout.setTitle("About");
				alertAbout.setMessage(Html.fromHtml("InstansiPadang<br><br>Version:1.0<br><br>Credits:<br>--------<br>Radhi Anshari<br>Politeknik Negeri Padang<br><br>Thanks to:<br>----------<br>Rita Afyenni, S.Kom. M.Kom<br>Ir. H.A Modutoo, M.Kom<br>All My Friends"));
				alertAbout.setIcon(R.drawable.ic_launcher);
				alertAbout.setCancelable(true);
				alertAbout.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
						
					}
				});
				
				AlertDialog alert = alertAbout.create();
				alert.show();
				
			}
		});
		
		ImageView btnmap = (ImageView)findViewById(R.id.imageButton3);
		btnmap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			Intent i = new Intent(MainActivity.this, Peta.class);
			
			startActivity(i);
			}
		});
		
		
		ImageView btnp = (ImageView)findViewById(R.id.imageButton1);
		btnp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			Intent i = new Intent(MainActivity.this, Padang.class);
			
			startActivity(i);
			}
			
			
		});
		
		ImageView btnpt = (ImageView)findViewById(R.id.imageButton5);
		btnpt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			Intent i = new Intent(MainActivity.this, Petunjuk.class);
			
			startActivity(i);
			}
			
			
		});
		

		
		ImageView btne = (ImageView)findViewById(R.id.imageButton6);
		btne.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);

				// set title
				alertDialogBuilder.setTitle("Keluar");

				// set dialog message
				alertDialogBuilder
						.setMessage("Yakin ingin keluar ?")
						.setCancelable(false)
						.setPositiveButton("Ya",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, close
										// current activity
										MainActivity.this.finish();
									}
								})
						.setNegativeButton("Tidak",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just
										// close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}

		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
