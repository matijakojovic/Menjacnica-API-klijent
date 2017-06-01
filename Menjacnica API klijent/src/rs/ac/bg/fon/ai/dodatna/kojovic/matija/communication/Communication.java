package rs.ac.bg.fon.ai.dodatna.kojovic.matija.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.*;


import rs.ac.bg.fon.ai.dodatna.kojovic.matija.domain.Zemlja;

public class Communication {
	
	public static LinkedList<Zemlja> zemlje(){
		String url = "http://free.currencyconverterapi.com/api/v3/countries";
		try{
			String out = sendGet(url);
			Gson gson = new GsonBuilder().create();
			
			JsonObject jsonResult = gson.fromJson(out, JsonObject.class);
			JsonObject result = (JsonObject) jsonResult.get("results");
			
			Set<Entry<String, JsonElement>> set = result.entrySet();
			LinkedList<Zemlja> lst = new LinkedList<Zemlja>();		
			
			for (Entry<String, JsonElement> entry : set) {
				JsonObject o = (JsonObject) entry.getValue();
				Zemlja z = new Zemlja(o.get("name").getAsString(), o.get("currencyId").getAsString());
				lst.add(z);
			}
			return lst;
		}catch (IOException e) {
			return null;
		}
		
	}

	private static String sendGet(String url) throws IOException{
		URL u = new URL(url);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		
		c.setRequestMethod("GET");
		 
		 BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		 boolean end = false;
		 String response = "";
		 
		 while(!end){
			 String s = in.readLine();
			 if(s == null){
				 end = true;
			 }else{
				 response += s;
			 }
			 
		 }
		 in.close();
		 return response.toString();
	}
	
	public static double odnosValuta(String iz, String u){
		
		String url = "http://free.currencyconverterapi.com/api/v3/convert?q=" + iz + "_" + u;
		try {
			String out = sendGet(url);
			Gson gson = new GsonBuilder().create();
			
			JsonObject o = gson.fromJson(out, JsonObject.class);
			int i = (((JsonObject) o.get("query")).get("count")).getAsInt();
		
			if(i == 0){
				return 0;
			}
			JsonObject jsonKurs = (JsonObject) (((JsonObject) o.get("results")).get(iz + "_" + u));
			double kurs = jsonKurs.get("val").getAsDouble();
			
			return kurs;
		} catch (IOException e) {
			return 0;
		}
	}
}
