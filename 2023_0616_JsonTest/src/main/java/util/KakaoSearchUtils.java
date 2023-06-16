package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.LocationVo;

public class KakaoSearchUtils {

	public static List<LocationVo> searchKeyword(String query,double latitude,double longitude,int page,int size){
	
		List<LocationVo>List =new ArrayList<LocationVo>();
		
	    try {
	    	
	    	query = URLEncoder.encode(query,"utf-8");
	    	
			String str_url = String.format("https://dapi.kakao.com/v2/local/search/keyword.json?page=%d&size=%d&sort=distance&query=%s&y=%.6f&x=%.7f",
					                                                                                page,   size,                   query,latitude,longitude      
					);
			    
			URL url = new URL(str_url);
			
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			
			//kakao에서 인증키 설정
			conn.setRequestProperty("Authorization", "KakaoAK 5010a54d67bb16fa8e767c3ab87b77cd");
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.connect();
			
			
			InputStream is = conn.getInputStream();//url.openStream();
			
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			
			BufferedReader    br  = new BufferedReader(isr);
			
			StringBuffer sb = new StringBuffer();
			
			while(true) {
				
				String data = br.readLine();
				//System.out.println(data);
				
				if(data==null) break;
				
				sb.append(data);
			}
			
			
			String json_data = sb.toString();
			
			//System.out.println(json_data);
			
			//json.paring
			JSONObject Json =new JSONObject(json_data);
			
			
			JSONArray localArray=Json.getJSONArray("documents");
			
			for(int i=0; i<localArray.length();i++) {
				
				JSONObject local =localArray.getJSONObject(i);
				
				String place_name=local.getString("place_name");
				String place_url=local.getString("place_url");
				String address_name=local.getString("address_name");
				String road_address_name=local.getString("road_address_name");
				String phone=local.getString("phone");
				int 	distance 			=0;
				double local_latitude		=0;
				double local_longlitude		=0;
				
				
				try {
					distance = local.getInt("distance");
					local_latitude =local.getDouble("y");
					local_longlitude =local.getDouble("x");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//LocationVo 생성 및 값을 넣는다 
				LocationVo vo =new LocationVo();
				vo.setPlace_name(place_name);
				vo.setPlace_url(place_url);
				vo.setRoad_address_name(road_address_name);
				vo.setPhone(phone);
				vo.setDistance(distance);
				vo.setLatitude(local_latitude);
				vo.setLongitude(longitude);
				
				List.add(vo);
				
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return List;
	}
}
