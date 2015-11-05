package com.model.instansipadang;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Model_Kantor{
	
	
	private String nama_kantor;
    private String foto;
    private String idkategori;
    private String lokasi;
	private String idkantor;
	
	
	
	
    public String getIdkantor() {
		return idkantor;
	}

	public void setIdkantor(String idkantor) {
		this.idkantor = idkantor;
	}

	public String getNama_kantor() {
		return nama_kantor;
	}

	public void setNama_kantor(String nama_kantor) {
		this.nama_kantor = nama_kantor;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getIdkategori() {
		return idkategori;
	}

	public void setIdkategori(String idkategori) {
		this.idkategori = idkategori;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	
   
    
   
	
	
	public static List<Model_Kantor> parseFeed(String response) {

		try {
			JSONObject object = new JSONObject(response);
			return parseFeed (object);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model_Kantor> parseFeed(JSONObject obj) {
        try {
           
            JSONArray arrayObj = obj.getJSONArray("data");

            List<Model_Kantor> kantorList = new ArrayList<Model_Kantor>();

            for (int i = 0; i < arrayObj.length(); i++) {
                JSONObject kantorObj = arrayObj.getJSONObject(i);
                Model_Kantor kantor = new Model_Kantor();
                
                kantor .setIdkantor(kantorObj.getString("id_kantor"));
                kantor .setIdkategori(kantorObj.getString("id_kategori"));
                kantor .setNama_kantor(kantorObj.getString("nama_kantor"));
                kantor .setLokasi(kantorObj.getString("lokasi"));
                kantor .setFoto(kantorObj.getString("foto"));
               
              
                kantorList.add(kantor);
            }
            return kantorList;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

}
