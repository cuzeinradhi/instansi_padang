package com.main.instansipadang;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.instansipadang.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.util.instansipadang.HttpManager;
import com.util.instansipadang.JSONParser;
import com.util.instansipadang.UrlKantor;

public class Maps extends FragmentActivity implements
		LocationListener, OnClickListener {
	
	private GoogleMap googleMap;
	String idkantor="";
	double latitude, longitude;
	private ProgressDialog pDialog;
	JSONArray college = null;
	HashMap<String,String> map;
	 HttpManager httpManager;
	 
	 ArrayList<HashMap<String,String >> dataList = new ArrayList<HashMap<String, String>>();
	
	 LatLng Padang;
	 
	@Override
	protected void onCreate(Bundle arg0) {
		CekGPS();
		super.onCreate(arg0);
		setContentView(R.layout.lokasi_maps);

		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
		.findFragmentById(R.id.map);
		
		googleMap = fm.getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		googleMap.setMyLocationEnabled(true);	
		
		new Data().execute();
		LatLng Padang = new LatLng(0.1116697,99.7768027);
		
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Padang,8));
	}
	
	public class Data extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Maps.this);
			pDialog.setMessage("Loading Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			String url;
			Intent intent = getIntent();
			idkantor = intent.getStringExtra("id_kantor");
			httpManager = new HttpManager(getApplicationContext());
	        
			Log.d("id lokasi", idkantor);
	        url = UrlKantor.URL_GET_MAPS +idkantor;

	        Log.d("Url Detail lokasi ==>", url);

			JSONParser jParser = new JSONParser();

			JSONObject json = jParser.getJSONFromUrl(url);
			try {
				college = json.getJSONArray("instansi_padang");
				Log.e("error", json.getString("success"));

				for (int i = 0; i <= college.length(); i++) {
					
					JSONObject c = college.getJSONObject(i);
					
					map = new HashMap<String, String>();

					String id_1 = c.getString("id_kantor").trim();
					String latitude_1 = c.getString("latitude").trim();
					String longitude_1 = c.getString("longitude").trim();
					String nama_1 = c.getString("nama_kantor").trim();
					
					map.put("id_kantor", id_1);
					map.put("nama_kantor", nama_1);
					map.put("latitude", latitude_1);
					map.put("longitude", longitude_1);

					dataList.add(map);

				}
			} catch (JSONException e) {

			}

			return null;
		}
	
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();

			for (int x = 0; x < dataList.size(); x = x + 1) {

				double latasal = Double.parseDouble(dataList.get(x).get(
						"latitude"));
				double longasal = Double.parseDouble(dataList.get(x).get(
						"longitude"));
				LatLng posisi = new LatLng(latasal, longasal);
				String nama = dataList.get(x).get("nama_kantor");
	
				float warna;
				googleMap.addMarker(new MarkerOptions()
						.position(posisi)
						.title(nama));
				warna=BitmapDescriptorFactory.HUE_RED;

			}

		}
	}				
	
	public void CekGPS() {
        try {

                /* pengecekan GPS hidup / tidak */
                LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                
                
                
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Info");
                        builder.setMessage("Anda akan mengaktifkan GPS?");

                        builder.setPositiveButton("Ya",
                                        new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                int which) {
                                                	
                                                        Intent i = new Intent(
                                                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                                        
                                                        if(latitude!=0 && longitude !=0){
                                            				Toast.makeText(getApplicationContext(), "Latitude : "+latitude+" Longitude : "+longitude, Toast.LENGTH_LONG).show();
                                            				
                                            			}
                                                        
                                                        startActivity(i);
                                                }
                                        });
                        

                        builder.setNegativeButton("Tidak",
                                        new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                int which) {
                                                        
                                                        dialog.dismiss();
                                                }
                                        });
                        builder.create().show();
                        
                        

                }

        } catch (Exception e) {
               
        }

        int status = GooglePlayServicesUtil
                        .isGooglePlayServicesAvailable(getBaseContext());

        // menampilkan status google play service
        if (status != ConnectionResult.SUCCESS) { 
                int requestCode = 10;
                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
                                requestCode);
                dialog.show();

        } else { 
        	// Google Play Services tersedia

                try {
                        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                        // Membuat kriteria untuk penampungan provider
                        Criteria criteria = new Criteria();

                        // Mencari provider terbaik
                        String provider = locationManager.getBestProvider(criteria,
                                        true);

                        // Mendapatkan lokasi terakhir
                        Location location = locationManager
                                        .getLastKnownLocation(provider);

                        if (location != null) {
                                onLocationChanged(location);
                        }
                        locationManager.requestLocationUpdates(provider, 5000, 0, this);
                } catch (Exception e) {
                     

                }
        }
        
        
	}

	
	@Override
	public void onLocationChanged(Location lokasi) {
		// TODO Auto-generated method stub
		latitude= lokasi.getLatitude();
		longitude=lokasi.getLongitude();

	}
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	
}
