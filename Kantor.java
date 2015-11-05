package com.main.instansipadang;

import com.example.instansipadang.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Kantor extends TabActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_kantor_activity);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		
TabHost tabHost = getTabHost();
        
        // Tab for Camat
        TabSpec photospec = tabHost.newTabSpec("Kantor Camat");
        photospec.setIndicator("Kantor Camat", getResources().getDrawable(R.drawable.ic_launcher));
        Intent photosIntent = new Intent(this, TabCamat.class);
        photospec.setContent(photosIntent);
        
        // Tab for Pemkot
        TabSpec songspec = tabHost.newTabSpec("Pemkot");
        // setting Title and Icon for the Tab
        songspec.setIndicator("Pemkot", getResources().getDrawable(R.drawable.ic_launcher));
        Intent songsIntent = new Intent(this, TabPemkot.class);
        songspec.setContent(songsIntent);
        
        // Tab for Pemprov
        TabSpec videospec = tabHost.newTabSpec("Pemprov");
        videospec.setIndicator("Pemprov", getResources().getDrawable(R.drawable.ic_launcher));
        Intent videosIntent = new Intent(this, TabPemprov.class);
        videospec.setContent(videosIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    onBackPressed();
	    return true;
	}
}
