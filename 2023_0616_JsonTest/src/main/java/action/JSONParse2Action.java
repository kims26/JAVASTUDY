package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class JSONParse2Action
 */
@WebServlet("/json2.do")
public class JSONParse2Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
        String str_url = "http://localhost:9090/2023_0616_JsonTest/json_data.jsp";
        
        URL url = new URL(str_url);
        
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
        
        System.out.println(json_data);
        
        
   //JSONParsing ##################################################3
        JSONObject json = new JSONObject(json_data);
        
        JSONObject nameJson = json.getJSONObject("name");
        String fullName   = nameJson.getString("fullName");
        String familyName = nameJson.getString("familyName");
        String givenName  = nameJson.getString("givenName");
        
        int age			 	= json.getInt("age");
        String addr 		= json.getString("addr");
        
        
        //Ãë¹Ì ÆÄ½Ì
        JSONArray hobbyArray = json.getJSONArray("hobby");
                
        StringBuffer sb_hobby = new StringBuffer();
        
        for(int i=0;i< hobbyArray.length();i++) {
        	
        	String hobby = hobbyArray.getString(i);
        	sb_hobby.append(hobby);
        	
        	if(i < hobbyArray.length()-1)
        	   sb_hobby.append("/");
        	
        }
        
        String hobby_list = sb_hobby.toString();
        
        
  //request binding ##########################################
        request.setAttribute("fullName", fullName);
        request.setAttribute("familyName", familyName);
        request.setAttribute("givenName", givenName);
        
        request.setAttribute("age", age);
        request.setAttribute("addr", addr);
        request.setAttribute("hobby_list", hobby_list);
                
        
        
		//Dispatcher
		String forward_page = "result_json2.jsp";
		request.getRequestDispatcher(forward_page).forward(request, response);

	}
}