package com.main.instansipadang;


import com.example.instansipadang.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;


public class SplashScreen extends Activity{

	private static int splashInterval = 2000;
	ProgressBar prg;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
	
		setContentView(R.layout.activity_splash);
		
		prg = (ProgressBar)findViewById(R.id.progressBar1);
		prg.setAlpha(splashInterval);
	
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent mainIntent = null;

				mainIntent = new Intent(SplashScreen.this,
					MainActivity.class);

				SplashScreen.this.startActivity(mainIntent);
				SplashScreen.this.finish();
			}
		}, splashInterval);
	}
}
