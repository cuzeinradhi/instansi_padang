package com.adapter.instansipadang;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.instansipadang.R;
import com.model.instansipadang.Model_Kantor;
import com.squareup.picasso.Picasso;
import com.util.instansipadang.UrlKantor;



public class Adapter_Kantor extends ArrayAdapter<Model_Kantor> {
    private Context context;
    private List<Model_Kantor> kantorList;
    String idkantor;
 

    
    
    public Adapter_Kantor(Context context, int resource, List<Model_Kantor> object) {
        super(context, resource, object);
        
        this.kantorList = object;
        this.context = context;
    }
    
   

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    		
    		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    		
    		View view = inflater.inflate(R.layout.item_tab_kantor,parent,false);
    		
    		Model_Kantor kantorData = kantorList.get(position);
    		
    			TextView namakantor = (TextView)view.findViewById(R.id.namakantor);
    	
    	        namakantor.setText(kantorData.getNama_kantor());
    	        
    	        
    	        TextView lokasi = (TextView)view.findViewById(R.id.lokasi);
    	    	
    	        lokasi.setText(kantorData.getLokasi());

    	        
    	        ImageView fotoo = (ImageView)view.findViewById(R.id.fotokantor);
    	        
                Picasso.with(this.context).load(UrlKantor.URL_FOTO_KANTOR + kantorData.getFoto()).resize(50, 50).into(fotoo);
               
    	
    	
    	
    	return view;
        
    }
    
   /* static class NewsHolder{
    	
    	TextView itemNama;
    	ImageView foto;*/
    	
    }
    
   
